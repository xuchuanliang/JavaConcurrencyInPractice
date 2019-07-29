package ThreadPattenByPic.capter05;

import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * @author xuchuanliangbt
 * @title: ConsumerThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2517:08
 * @Version
 */
public class ConsumerThread extends Thread{
    private final Exchanger<char[]> exchanger;
    private char[] buffer;
    private final Random random;
    public ConsumerThread(Exchanger<char[]> exchanger,char[] buffer,long seed){
        super("consumerThread");
        this.exchanger = exchanger;
        this.buffer = buffer;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        try {
            while (true){
                //交换缓冲区
                System.out.println(Thread.currentThread().getName()+":BEFORE EXCHANGE");
                buffer = exchanger.exchange(buffer);
                System.out.println(Thread.currentThread().getName()+":AFTER EXCHANGE");
                //从缓冲区取出字符串
                for(int i=0;i<buffer.length;i++){
                    System.out.println(Thread.currentThread().getName()+":->"+buffer[i]);
                    Thread.sleep(random.nextInt(1000));
                }
            }
        }catch (InterruptedException e){

        }
    }
}
