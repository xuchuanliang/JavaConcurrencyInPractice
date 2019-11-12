package JavaCoreThreadPatten.capter01;

public class WelcomeApp1 {
    public static void main(String[] args){
        Thread thread = new Thread(new WelcomeThread1());
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
