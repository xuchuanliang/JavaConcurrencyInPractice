package ThreadPattenByPic.capter03;

import java.util.Random;

/**
 * @author xuchuanliangbt
 * @title: ServerThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2417:53
 * @Version
 */
public class ServerThread2 extends Thread{

    private final Random random;
    private final RequestQueue2 requestQueue;

    public ServerThread2(RequestQueue2 requestQueue, String name, long seed) {
        super(name);
        this.random = new Random(seed);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        for(int i=0;i<1000;i++){
            Request request = requestQueue.getRequest();
            System.out.println(Thread.currentThread().getName()+" handles "+request);
            try{
                Thread.sleep(random.nextInt(1000));
            }catch (InterruptedException e){}
        }
    }
}
