package utils;

import java.util.List;

public class Utils {
    public static <T> T[] listToArray(List<T> list) {
        // Se crea un arreglo del tamanio de la lista
        T[] array = (T[]) new Object[list.size()];

        int i = 0;
        for (T iterador : list) {
            array[i++] = iterador;
        }
        return array;
    }
}

// Quedamos en el minuto 15 https://www.youtube.com/@kevinwifredmejiatorres795
