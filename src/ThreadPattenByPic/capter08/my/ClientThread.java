package ThreadPattenByPic.capter08.my;

import java.util.Random;

/**
 * @author xuchuanliangbt
 * @title: ClientThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/3117:52
 * @Version
 */
public class ClientThread extends Thread{

    private final Channel channel;
    private static final Random RANDOM = new Random();
    public ClientThread(String name,Channel channel){
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        int i = 1;
        while (true){
            try {
                channel.putRequest(new Request(getName(),i++));
                Thread.sleep(RANDOM.nextInt(1000));
            } catch (InterruptedException e) {
            }
        }
    }
}
