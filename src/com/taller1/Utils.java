package com.taller1;

import java.util.Calendar;
import java.util.Date;

/**
 * distintos metodos auxiliares
 * @author denis
 */
public class Utils {
    /**
     * Entrega el saludo correspondiente a la hora local.
     * diferencia entre maniana, tarde y noche.
     * @return el saludo apropiado segun la hora del dia -maniana, tarde, noche-.
     */
    public static String saludo() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if(hour >= 6 && hour <= 12)
        {
            return "Buenos Dias";
        } else if (hour >= 12 && hour <= 20) {
            return "Buenas Tardes";
        }
        return "Buenas Noches";
    }

}
