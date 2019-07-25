package ThreadPattenByPic.capter03;

/**
 * @author xuchuanliangbt
 * @title: Request
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2417:37
 * @Version
 */
public class Request {
    private final String name;

    public Request(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                '}';
    }
}
