package JavaCoreThreadPatten.capter07.v2;

import java.util.Objects;

/**
 * 通过粗粒度的锁规避死锁
 */
public class GlobalLckBasedPhilosopher extends AbstractPhilosopher{
    private static final Object LOCK = new Object();

    public GlobalLckBasedPhilosopher(int id, Chopstick left, Chopstick right) {
        super(id, left, right);
    }

    @Override
    public void eat() {
        synchronized (LOCK){
            System.out.println("获取到全局锁");
            left.pickUp();
            right.pickUp();
            doEat();
            right.putDown();
            left.putDown();
            System.out.println("哲学家"+id+"吃饭完毕");
        }
        think();
    }
}
