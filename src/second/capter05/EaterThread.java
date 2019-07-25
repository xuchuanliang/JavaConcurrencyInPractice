package second.capter05;

import java.util.Random;

/**
 * @author xuchuanliangbt
 * @title: EaterThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2515:50
 * @Version
 */
public class EaterThread extends Thread{

    private final Table table;
    private final Random random;

    public EaterThread(String name,Table table, long seed) {
        super(name);
        this.table = table;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        while (true){
            try {
                table.take();
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
