package JavaCoreThreadPatten.capter07.v2;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 哲学家抽象类
 */
public abstract class AbstractPhilosopher extends Thread {

    protected final int id;
    protected final Chopstick left;
    protected final Chopstick right;

    public AbstractPhilosopher(int id, Chopstick left, Chopstick right) {
        this.id = id;
        this.left = left;
        this.right = right;
    }

    public abstract void eat();

    public void think(){
        try {
            System.out.println("我是哲学家："+id+",我开始思考。。。");
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for(;;){
            eat();
            think();
        }
    }

    public void doEat(){
        try {
            System.out.println("我是哲学家："+id+",我开始吃饭。。。");
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
