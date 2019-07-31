package ThreadPattenByPic.capter07.v2;

import java.util.concurrent.Executors;

/**
 * @author xuchuanliangbt
 * @title: MainV2
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/318:24
 * @Version
 */
public class MainV2 {
    public static void main(String[] args){
        System.out.println("main BEGIN");
        Host host = new Host(Executors.defaultThreadFactory());
        host.request(10,'A');
        host.request(20,'B');
        host.request(30,'C');
        System.out.println("main END");
    }
}
