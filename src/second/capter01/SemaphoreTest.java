package second.capter01;

import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

/**
 * 信号量
 */
public class SemaphoreTest {
    public static void main(String[] args){
        BoundResource boundResource = new BoundResource(3);
        for(int i=0;i<10;i++){
            new UseThread(boundResource).start();
        }
    }
}
class Log{
    public static void pritln(String s){
        System.out.println(Thread.currentThread().getName()+":"+s);
    }
}

/**
 * 资源个数有限
 */
class BoundResource{
    //信号量
    private final Semaphore semaphore;
    //资源个数
    private final int permits;
    private final static Random RANDOM = new Random(314159);

    public BoundResource(int permits) {
        semaphore = new Semaphore(permits);
        this.permits = permits;
    }
    public  void use() throws InterruptedException {
        semaphore.acquire();
        try{
            doUse();
        }finally {
            semaphore.release();
        }
    }
    protected void doUse() throws InterruptedException {
        Log.pritln("BEGIN:used="+(permits-semaphore.availablePermits()));
        Thread.sleep(RANDOM.nextInt(500));
        Log.pritln("END:used="+(permits-semaphore.availablePermits()));
    }
}

class UseThread extends Thread{

    private final static Random RANDOM = new Random(26535);
    private final BoundResource boundResource;

    public UseThread(BoundResource boundResource) {
        this.boundResource = boundResource;
    }

    @Override
    public void run() {
        while (true){
            try {
                boundResource.use();
                Thread.sleep(RANDOM.nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
