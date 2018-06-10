package capter05.com.snail.b01;

import java.util.Vector;

public class VectorySafe {
    public static Object getLast(Vector list){
        synchronized (list){
            int lastIndex = list.size()-1;
            return list.get(lastIndex);
        }
    }

    public static void deleteLast(Vector list){
        synchronized (list){
            int lastIndex = list.size()-1;
            list.remove(lastIndex);
        }
    }
}
