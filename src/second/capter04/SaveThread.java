package second.capter04;

import java.io.IOException;

/**
 * @author xuchuanliangbt
 * @title: SaveThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2514:49
 * @Version
 */
public class SaveThread extends Thread{

    private final Data data;

    public SaveThread(Data data,String name) {
        super(name);
        this.data = data;
    }

    @Override
    public void run() {
        while (true){
            try {
                data.save();
                Thread.sleep(1000);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
