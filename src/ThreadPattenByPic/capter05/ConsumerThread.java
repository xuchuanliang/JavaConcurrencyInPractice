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
                System.out.println(Thread.currentThread().getName()+":");
            }
        }catch (InterruptedException e){

        }
    }
}
