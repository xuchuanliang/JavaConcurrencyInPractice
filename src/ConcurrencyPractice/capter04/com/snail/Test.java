package ConcurrencyPractice.capter04.com.snail;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    public static void main(String[] args){
        unmodifiableMapTest();
    }
    public static void unmodifiableMapTest(){
//        Map<String,MutablePoint> stringMap = new HashMap<>();
//        MutablePoint mutablePoint = new MutablePoint();
//        mutablePoint.x=1;
//        mutablePoint.y=1;
//        stringMap.put("m",mutablePoint);
//        Map<String,MutablePoint> unmMap = Collections.unmodifiableMap(stringMap);
//        mutablePoint.x=2;
//        mutablePoint.y=2;
//        System.out.print(unmMap);

//        Map<String,String> first = new HashMap<>();
//        Map<String,String> unm = Collections.unmodifiableMap(first);
//        first.put("1","1");
//        first.put("2","2");
//        first.put("1","3");
//        first.put("3","3");
//        System.out.println(first);
//        System.out.println(unm);
//        System.out.println(first==unm);
//
//        Point point1 = new Point(1,1);
//        Point point2 = point1;
//        System.out.println(point1==point2);
//
//        Map<String,String> second = new HashMap<>(first);
//
//        System.out.println(second);
//        first.put("4","4");
//        System.out.println(second);
//        System.out.println(first);

        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();
        Map<String,String> map = Collections.unmodifiableMap(concurrentHashMap);
        concurrentHashMap.put("徐传良","大笨蛋");
        Map<String,String> map1 = new HashMap<>(concurrentHashMap);
        System.out.println(map);
        System.out.println(map1);
        concurrentHashMap.put("徐传良","大傻子");
        System.out.println(map);
        System.out.println(map1);


    }


}
