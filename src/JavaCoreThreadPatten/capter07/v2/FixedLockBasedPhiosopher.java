package JavaCoreThreadPatten.capter07.v2;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class FixedLockBasedPhiosopher extends AbstractPhilosopher{

    private static final ConcurrentHashMap<Chopstick, ReentrantLock> LOCK_MAP = new ConcurrentHashMap<>();

    public FixedLockBasedPhiosopher(int id, Chopstick left, Chopstick right) {
        super(id, left, right);
        LOCK_MAP.putIfAbsent(left,new ReentrantLock());
        LOCK_MAP.putIfAbsent(right,new ReentrantLock());
    }

    @Override
    public void eat() {
        ReentrantLock leftLock = LOCK_MAP.get(left);
        ReentrantLock rightLock = LOCK_MAP.get(right);
        try {
            boolean b = leftLock.tryLock(1, TimeUnit.SECONDS);
            if(b){
                System.out.println("哲学家"+id+"获取到左边的锁，嘚瑟一下");
                left.pickUp();
                b = rightLock.tryLock(1,TimeUnit.SECONDS);
                if(b){
                    right.pickUp();
                    System.out.println("哲学家"+id+"获取到又边的锁，吃饭。。。");
                    doEat();
                    rightLock.unlock();
                }
                leftLock.unlock();
            }
        } catch (InterruptedException e) {
            if(leftLock.isLocked()){
                leftLock.unlock();
            }
            if(rightLock.isLocked()){
                rightLock.unlock();
            }
        }
    }

    @Override
    public void run() {
        for(;;){
            eat();
            think();
        }
    }
}
