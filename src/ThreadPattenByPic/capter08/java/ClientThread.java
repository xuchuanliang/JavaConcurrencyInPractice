package ThreadPattenByPic.capter08.java;

import ThreadPattenByPic.capter08.my.Channel;

import java.util.Random;
import java.util.concurrent.ExecutorService;

/**
 * @author xuchuanliangbt
 * @title: ClientThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/3117:52
 * @Version
 */
public class ClientThread extends Thread{

    private final ExecutorService executorService;
    private static final Random RANDOM = new Random();
    public ClientThread(String name, ExecutorService executorService){
        super(name);
        this.executorService = executorService;
    }

    @Override
    public void run() {
        int i = 1;
        while (true){
            try {
                executorService.execute(new Request(getName(),i++));
                Thread.sleep(RANDOM.nextInt(1000));
            } catch (InterruptedException e) {
            }
        }
    }
}
