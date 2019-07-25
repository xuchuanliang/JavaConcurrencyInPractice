package ThreadPattenByPic.capter05;

import java.util.Random;

/**
 * @author xuchuanliangbt
 * @title: MakerThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2515:52
 * @Version
 */
public class MakerThread extends Thread{

    private final Table table;
    private final Random random;
    private static int id;

    public MakerThread(String name,Table table, long seed) {
        super(name);
        this.table = table;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(random.nextInt(1000));
                String cake = "[cake No."+nextId()+" by "+Thread.currentThread().getName()+"]";
                table.put(cake);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int nextId(){
        id++;
        return id;
    }
}
