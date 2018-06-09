package capter04.com.snail;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 线程安全的内置锁实例
 */
public class MonitorVehicleTracker {
    private final Map<String,MutablePoint> locations;

    public MonitorVehicleTracker(Map<String,MutablePoint> locations){
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String,MutablePoint> getLocations(){
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id){
        MutablePoint loc = locations.get(id);
        return loc==null?null:new MutablePoint(loc);
    }

    public synchronized  void getLocation(String id,int x,int y){
        MutablePoint loc = locations.get(id);
        if(loc==null){
            throw new IllegalArgumentException("no such id:"+id);
        }
        loc.x = x;
        loc.y = y;
    }

    public static Map<String,MutablePoint> deepCopy(Map<String,MutablePoint> m){
        Map<String,MutablePoint> result = new HashMap<>();
        for(String id:m.keySet()){
            result.put(id,new MutablePoint(m.get(id)));
        }
        return Collections.unmodifiableMap(result);
    }

}
