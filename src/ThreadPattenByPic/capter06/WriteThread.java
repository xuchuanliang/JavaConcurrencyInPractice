package ThreadPattenByPic.capter06;

import java.util.Random;

/**
 * @author xuchuanliangbt
 * @title: WriteThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2917:42
 * @Version
 */
public class WriteThread extends Thread {
    private static final Random RANDOM = new Random();
    private final Data data;
    private final String filter;
    private int index = 0;

    public WriteThread(Data data, String filter) {
        this.data = data;
        this.filter = filter;
    }

    @Override
    public void run() {
        while (true){
            try {
                System.out.println(Thread.currentThread().getName()+" start writing");
                data.write(nextChar());
                Thread.sleep(RANDOM.nextInt(3000));
            } catch (InterruptedException e) {
            }
        }
    }

    private char nextChar() {
        char c = filter.charAt(index);
        index = (index++) % filter.length();
        return c;
    }
}
