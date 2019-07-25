package ConcurrencyPractice.capter03.code;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public final InnerTest innerTest = new InnerTest("snail","25");
    public Map<String,Date> lastLogin = Collections.synchronizedMap(new HashMap<>());

}
final class InnerTest{
    private String name;
    private String id;

    public InnerTest(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
