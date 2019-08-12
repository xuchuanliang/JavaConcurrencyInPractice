package ThreadPattenByPic.capter11.t2;

import java.util.Random;

/**
 * @author xuchuanliangbt
 * @title: ClientThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1212:33
 * @Version
 */
public class ClientThread extends Thread{

    Random random = new Random();

    public ClientThread(String name){
        super(name);
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            Log.print(String.valueOf(i));
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
