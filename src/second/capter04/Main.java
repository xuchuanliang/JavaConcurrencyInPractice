package second.capter04;

import java.util.concurrent.TimeoutException;

/**
 * @author xuchuanliangbt
 * @title: Main
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2514:54
 * @Version
 */
public class Main {
    public static void main(String[] args){
        test2();
    }

    public static void test1(){
        Data data = new Data("D:/data.txt","徐传良大帅哥");
        new ChangeThread(data,"change").start();
        new SaveThread(data,"save").start();
    }

    public static void test2(){
        Host host = new Host(10000);
        System.out.println("BEGIN");
        try {
            host.execute();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
