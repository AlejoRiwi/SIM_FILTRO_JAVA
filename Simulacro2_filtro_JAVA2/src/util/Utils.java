package util;

import java.util.List;

public class Utils {
    public static <T> T[] lisToArray(List<T> list) {
        T[] array = (T[]) new Object[list.size()];
        int i = 0;
        for(T iterator : list) {
            array[i++] = iterator;
        }
        return array;
    }
}
