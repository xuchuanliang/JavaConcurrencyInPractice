package JavaCoreThreadPatten.capter01;

import java.util.concurrent.TimeUnit;

public class WelcomeApp {
    public static void main(String[] args){
        Thread thread = new WelcomeThread();
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.start();
        System.out.println("父线程："+Thread.currentThread().getName());
    }
}
class WelcomeThread extends Thread{
    @Override
    public void run() {
        System.out.println("子线程："+Thread.currentThread().getName());
    }
}
