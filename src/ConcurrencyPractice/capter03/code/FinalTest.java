package ConcurrencyPractice.capter03.code;

import java.util.HashSet;
import java.util.Set;

public class FinalTest {
    public static void main(String[] args){
        T t = new T();
        Set<String> s = t.getS();
        s.add("snail4");
        System.out.print(s);
    }
}
class T{

    private final Set<String> s = new HashSet<>();

    public T(){
        s.add("snail1");
        s.add("snail2");
        s.add("snail3");
    }

    public Set<String> getS(){
        return s;
    }
}
