package ConcurrencyPractice.capter04.com.snail.b03;

import ConcurrencyPractice.capter03.anno.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 线程的安全委托
 */
@ThreadSafe
public class DelegatingVehicleTracker {
    private final ConcurrentHashMap<String,Point> locations;
    private final Map<String,Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String,Point> points){
        locations = new ConcurrentHashMap<>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String,Point> getLocations(){
        return unmodifiableMap;
    }

    public Point getLocation(String id){
        return locations.get(id);
    }

    public void setLocation(String id,int x,int y){
        if(locations.replace(id,new Point(x,y))==null)
            throw new IllegalArgumentException("invalid vehicle name:"+id);
    }

    public static void main(String[] args){
        Point p1 = new Point(1,1);
        Point p2 = new Point(2,2);
        Map<String,Point> points = new HashMap<>();
        points.put("1",p1);
        points.put("2",p2);

        DelegatingVehicleTracker delegatingVehicleTracker = new DelegatingVehicleTracker(points);

        System.out.println(delegatingVehicleTracker.getLocations());
        System.out.println(delegatingVehicleTracker.getLocation("1"));

        delegatingVehicleTracker.setLocation("1",3,4);

        System.out.println(delegatingVehicleTracker.getLocations());

        System.out.println(delegatingVehicleTracker.getLocation("1"));
    }

}
