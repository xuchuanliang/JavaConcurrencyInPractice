package JavaCoreThreadPatten.capter01;

public class WelcomeApp1 {
    public static void main(String[] args){
        Thread thread = new WelcomeThread();
        thread.start();
        System.out.println("父线程："+Thread.currentThread().getName());
    }
}

class WelcomeThread1 implements Runnable{
    @Override
    public void run() {
        System.out.println("子线程："+Thread.currentThread().getName());
    }
}
