package pe.gob.munihuacho.munimovil.util;

/**
 * Created by peral on 29/03/2017.
 */

public class Comunes {
    public static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }
}
