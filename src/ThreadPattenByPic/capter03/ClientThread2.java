package ThreadPattenByPic.capter03;

import java.util.Random;

/**
 * @author xuchuanliangbt
 * @title: ClientThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2417:49
 * @Version
 */
public class ClientThread2 extends Thread{
    private final Random random;
    private final RequestQueue2 requestQueue;

    public ClientThread2(RequestQueue2 requestQueue, String name, long seed) {
        super(name);
        this.random = new Random(seed);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        for(int i=0;i<10000;i++){
            Request request = new Request("NO."+i);
            System.out.println(Thread.currentThread().getName()+" request "+request);
            requestQueue.putRequest(request);
            try{
                Thread.sleep(random.nextInt(1000));
            }catch (InterruptedException e){}
        }
    }
}
