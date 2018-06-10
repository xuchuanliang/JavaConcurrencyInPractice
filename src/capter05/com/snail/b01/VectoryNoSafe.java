package capter05.com.snail.b01;

import java.util.Vector;

public class VectoryNoSafe {
    public static Object getLast(Vector list){
        int lastIndex = list.size()-1;
        return list.get(lastIndex);
    }

    public static void deleteLast(Vector list){
        int lastIndex = list.size()-1;
        list.remove(lastIndex);
    }
}
