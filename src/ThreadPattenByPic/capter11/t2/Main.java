package ThreadPattenByPic.capter11.t2;

/**
 * @author xuchuanliangbt
 * @title: Main
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1212:35
 * @Version
 */
public class Main {
    public static void main(String[] args){
        new ClientThread("tom").start();
        new ClientThread("jetty").start();
        new ClientThread("snail").start();
    }
}
