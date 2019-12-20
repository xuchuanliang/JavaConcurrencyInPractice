package JavaCoreThreadPatten.capter07.v2;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 导致死锁的哲学家模型
 */
public class DeadlockingPhilosopher extends AbstractPhilosopher{

    public DeadlockingPhilosopher(int id, Chopstick left, Chopstick right) {
        super(id, left, right);
    }

    @Override
    public void eat() {
        synchronized (super.left){
            System.out.println(id+"拿起左手边的筷子："+left);
            super.left.pickUp();
            synchronized (super.right){
                System.out.println(id+"拿起右手边的筷子："+right+";开始吃饭啦。。");
                super.right.pickUp();
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                } catch (InterruptedException e) {
                }
                super.right.putDown();
            }
            super.left.putDown();
        }
    }
}
