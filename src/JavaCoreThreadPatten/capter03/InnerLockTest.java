package JavaCoreThreadPatten.capter03;

import java.util.concurrent.CountDownLatch;

public class InnerLockTest {

    private static final Object OnlyObj = new Object();

    private Object lock = new Object();
    private int i = 0;
    public void add(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock){
            i++;
        }
    }

    public int getI() {
        return i;
    }

    public void setLock(Object lock) {
        this.lock = lock;
    }

    public static void main(String[] args) throws InterruptedException {
        InnerLockTest innerLockTest = new InnerLockTest();
        Thread thread = null;
        for(int i=0;i<100;i++){
            thread = new Thread(()->{
                innerLockTest.setLock(new Object());
                for(int j=0;j<10;j++){
                    innerLockTest.add();
                }
            });
            thread.start();
        }
        Thread.sleep(1000*10);
        System.out.println("end===========");
        System.out.println(innerLockTest.getI());
    }
}
