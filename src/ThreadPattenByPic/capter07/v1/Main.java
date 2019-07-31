package ThreadPattenByPic.capter07.v1;

/**
 * @author xuchuanliangbt
 * @title: Main
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/3018:11
 * @Version
 */
public class Main {
    public static void main(String[] args){
        System.out.println("main BEGIN");
        Host host = new Host();
        host.request(10,'A');
        host.request(20,'B');
        host.request(30,'C');
        System.out.println("main END");
    }
}
