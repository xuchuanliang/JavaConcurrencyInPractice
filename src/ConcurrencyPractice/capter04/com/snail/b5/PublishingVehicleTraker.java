package ConcurrencyPractice.capter04.com.snail.b5;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PublishingVehicleTraker {

    private final Map<String,SafePoint> locations;

    private final Map<String,SafePoint> unmodifyLocations;

    public PublishingVehicleTraker(Map<String,SafePoint> map){
        locations = new ConcurrentHashMap<>(map);
        unmodifyLocations = Collections.unmodifiableMap(locations);
    }

    public Map<String,SafePoint> getLocations(){
        return unmodifyLocations;
    }

    public SafePoint getLocation(String name){
        return locations.get(name);
    }

    public void setLocation(String name,int x,int y){
        locations.get(name).set(x,y);
    }
}
