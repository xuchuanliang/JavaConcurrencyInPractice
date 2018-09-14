package capter11.com.snail.b1;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 减少锁的持有时间
 */
public class BetterAttributeStore {
    private final Map<String,String> attribute = new HashMap<>();

    public boolean userLocationMatches(String name,String regexp){
        String key = "user."+name+".location";
        String location;
        synchronized (this) {
            location = attribute.get(key);
        }
        if(location==null){
            return false;
        }else{
            return Pattern.matches(regexp,location);
        }
    }
}
