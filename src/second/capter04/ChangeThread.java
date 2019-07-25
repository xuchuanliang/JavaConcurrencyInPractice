package second.capter04;

import java.io.IOException;
import java.util.Random;

/**
 * @author xuchuanliangbt
 * @title: ChangeThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2514:50
 * @Version
 */
public class ChangeThread extends Thread{

    private final Data data;
    private final Random random = new Random();

    public ChangeThread(Data data,String name) {
        super(name);
        this.data = data;
    }

    @Override
    public void run() {
        for (int i=0;true;i++){
            try {
                data.change("No:"+i+";");
                data.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
