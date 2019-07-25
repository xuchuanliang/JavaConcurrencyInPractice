package ConcurrencyPractice.capter04.com.snail.b5;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 委托模式，将线程安全性委托给变量locations
 * locations将线程安全性委托给point
 */
public class DeligatingVehicleTranker {

    private final ConcurrentHashMap<String,Point> locations;


    private final Map<String,Point> unmodifyLocations;

    public DeligatingVehicleTranker(Map<String,Point> map){
        locations = new ConcurrentHashMap<>(map);
        unmodifyLocations = Collections.unmodifiableMap(locations);
    }

    public Map<String,Point> getLocation(){
        return unmodifyLocations;
    }


}
