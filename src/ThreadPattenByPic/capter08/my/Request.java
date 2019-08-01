package ThreadPattenByPic.capter08.my;

import java.util.Random;

/**
 * @author xuchuanliangbt
 * @title: Request
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/3117:52
 * @Version
 */
public class Request {
    private final String name;
    private final int no;
    private final Random random = new Random();

    public Request(String name,int no) {
        this.name = name;
        this.no = no;
    }

    public void execute(){
        System.out.println(Thread.currentThread().getName()+" executes "+ toString());
        try{
            Thread.sleep(random.nextInt(100));
        }catch (Exception e){}
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                ", no=" + no +
                '}';
    }
}
