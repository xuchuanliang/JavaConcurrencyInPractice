package ConcurrencyPractice.capter11.com.snail.b1;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 将一个锁不必要的持有过长时间
 */
public class AttributeStore {
    private final Map<String,String> attribute = new HashMap<>();

    public synchronized boolean userLocationMatches(String name,String regexp){
        String key = "user."+name+".location";
        String location = attribute.get(key);
        if(location == null){
            return false;
        }else{
            return Pattern.matches(regexp,location);
        }
    }
}
