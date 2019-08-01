package ThreadPattenByPic.capter09;

/**
 * @author xuchuanliangbt
 * @title: Main
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/116:46
 * @Version
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main BEGIN");
        Host host = new Host();
        Data data1 = host.request(10,'A');
        Data data2 = host.request(20,'B');
        Data data3 = host.request(30,'B');
        System.out.println("main otherJob Begin");
        Thread.sleep(3000);
        System.out.println("main otherJob End");
        System.out.println("data1="+data1.getContent());
        System.out.println("data2="+data2.getContent());
        System.out.println("data3="+data3.getContent());
        System.out.println("main End");
    }
}
