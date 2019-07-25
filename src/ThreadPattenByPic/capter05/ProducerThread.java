package ThreadPattenByPic.capter05;

import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * @author xuchuanliangbt
 * @title: ProducerThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2516:58
 * @Version
 */
public class ProducerThread extends Thread{
    private final Exchanger<char[]> exchanger;
    private char[] buffer = null;
    private char index = 0;
    private final Random random;

    public ProducerThread(Exchanger<char[]> exchanger, char[] buffer,long seed) {
        super("ProducerThread");
        this.exchanger = exchanger;
        this.random = new Random(seed);
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true){
                //向缓冲区填充字符
                for(int i=0;i<buffer.length;i++){
                    buffer[i] = nextChar();
                    System.out.println(Thread.currentThread().getName()+":"+buffer[i]+"-->");
                }
                //交换缓冲区
                System.out.println(Thread.currentThread().getName()+": before exchange");
                buffer = exchanger.exchange(buffer);
                System.out.println(Thread.currentThread().getName()+": after exchange");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private char nextChar() throws InterruptedException {
        char c = (char)('A' + index % 26);
        index++;
        Thread.sleep(random.nextInt(1000));
        return c;
    }
}
