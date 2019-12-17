package JavaCoreThreadPatten.capter05;

import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("====");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        t.join();
        System.out.println("子线程执行结束，开始执行业务方法");
    }
}
