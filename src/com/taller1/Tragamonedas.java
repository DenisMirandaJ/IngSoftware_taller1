package com.taller1;

import java.util.Random;
import java.util.Scanner;

/**
 * Esta clase implementa el comportamiento basico de una maquina tragamonedas
 * compuesta por tres ruedas, cada una con numeros del 0 al 9
 * @author denis
 */
public class Tragamonedas {
    /**
     * Representacion de las ruedas de la maquina tragamonedas
     */
    private int[] casillas = new int[3];
    /**
     * saldo disponible del jugador
     */
    private int saldo;
    /**
     * en esta variable se guarda la apuesta actual de jugador
     */
    private int apuesta;
    /**
     * el premio obtenido por el jugador
     */
    private int premio;

    /**
     * El constructor inicializa el saldo inicial en 1000
     */
    public Tragamonedas() {
        saldo = 1000;
    }

    /**
     * Genera tres numeros aleatorios en el rango 0-9
     * estos numeros representan el estado de las ruedas del tragamonedas
     */
    private void rand() {
        Random rand = new Random();
        casillas[0] = rand.nextInt((9 - 0) + 1);
        casillas[1] = rand.nextInt((9 - 0) + 1);
        casillas[2] = rand.nextInt((9 - 0) + 1);
    }

    /**
     * Este metodo obtiene la apuesta del jugador, y asegura que sea valida
     * @exception NumberFormatException cuando el valor ingresado no es un entero
     * @return boolean retorna false en caso de error
     */
    private boolean ObtenerApuesta() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        try {
            apuesta = Integer.parseInt(input);
            if (apuesta < 0) {
                return false;
            }
            if (saldo < apuesta) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Calcula el premio segun el valor de las ruedas del tragamonedas
     */
    private void calcularPremio() {
        if (casillas[0] == casillas[1] && casillas[0] == casillas[2] && casillas[0] != 0) {
            premio = casillas[0]*apuesta;
            return;
        }
        int ast = 0;
        for (int i: casillas) {
            if (i == 0)
                ast += 1;
        }
        if (ast == 1)
            premio = 50;
        else if (ast == 2)
            premio = 300;
        else if (ast == 3)
            premio = 500;
        else {
            premio = 0;
        }
    }

    /**
     * este metodo imprime por pantalla todos los mensajes de la aplicacion
     * Codigos asociados a los mensajes:
     * "premio": indica el monto ganado como premio.
     * "casillas": indica el resultado de las ruedas del tragamonedas.
     * "error": se imprime en caso de error en las datos entregados por el usuario.
     * "salir": mensaje de despedida.
     * "gameover": al perder el juego.
     * "text": pregunta al usuario cuando desea apostar.
     * @param caso Indica que mensaje debe mostrarse, si no existe un codigo correspondiente no se imprime nada
     */
    private void render(String caso) {
        if (caso == "premio") {
            System.out.printf("Ud. Obtiene $%d!\n", premio);
            return;
        }
        if (caso == "casillas") {
            String text;
            System.out.println("\t+---+---+---+");
            text = String.format("\t| %d | %d | %d |\n", casillas[0],casillas[1],casillas[2]);
            text.replace("0","*");
            System.out.printf(text);
            System.out.println("\t+---+---+---+");
            return;
        }
        if (caso == "error") {
            System.out.println("Ingrese una apuesta valida, 0 para terminar");
            return;
        }
        if (caso == "salir") {
            System.out.printf("%s, Gracias por jugar. Su saldo final es de $%d.\n", Utils.saludo(), saldo);
            return;
        }
        if (caso == "gameover") {
            System.out.println("Muchas gracias por jugar. Mejor suerte la proxima vez.");
            return;
        }
        if (caso == "text") {
            System.out.printf("Su saldo actual es de $%d. ¿Cuanto desea apostar? \n> ", saldo);
            return;
        }
    }

    /**
     * Implementacion de la logica principal de cada turno en el juego
     * @return false si sse llego a una condicion de termino -El usuario decide salir o el saldo llega a 0-
     * true en caso contrario
     */
    private boolean turno() {
        render("text");
        if (!ObtenerApuesta()) {
            render("error");
            return true;
        }
        if (apuesta == 0) {
            render("salir");
            return false;
        }
        saldo = saldo - apuesta;
        rand();
        render("casillas");
        calcularPremio();
        if (premio > 0) {
            saldo += apuesta;
            render("premio");
        } else {
            if (saldo <= 0) {
                render("gameover");
                return false;
            }
        }
        saldo += premio;
        return true;
    }

    /**
     * Ejecuta el loop principal del juego hasta que se llegue a una condición de termino
     * condiciones de termino: el usuario decide salir o el saldo llega a 0.
     */
    public void play() {
        System.out.printf("");
        while (turno()) {
            ;
        }
    }



}

