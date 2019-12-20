package JavaCoreThreadPatten.capter07.v2;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 显示锁导致可能出现死锁问题的哲学家
 */
public class BuggyLckBasedPhilosopher extends AbstractPhilosopher{
    /**
     * 确保每个筷子有一个显示锁与之对应，则确保该类的每一个实例共享同一个锁map
     */
    private static final ConcurrentHashMap<Chopstick, Lock> LOCK_MAP;
    static {
        LOCK_MAP = new ConcurrentHashMap<>();
    }

    public BuggyLckBasedPhilosopher(int id, Chopstick left, Chopstick right) {
        super(id, left, right);
        LOCK_MAP.putIfAbsent(left,new ReentrantLock());
        LOCK_MAP.putIfAbsent(right,new ReentrantLock());
    }

    @Override
    public void eat() {
        if(pickUpChopstick(left) && pickUpChopstick(right)){
            doEat();
        }
        putDownChopstick(right);
        putDownChopstick(left);
        think();
    }

    protected boolean pickUpChopstick(Chopstick chopstick){
        final Lock lock = LOCK_MAP.get(chopstick);
        try{
            lock.lock();
            System.out.println("哲学家"+id+"捡起筷子"+chopstick);
            chopstick.pickUp();
            return true;
        }catch (Exception e){
            lock.unlock();
        }
        return false;
    }

    protected void putDownChopstick(Chopstick chopstick){
        final Lock lock = LOCK_MAP.get(chopstick);
        try{
            System.out.println("哲学家"+id+"放下筷子"+chopstick);
            chopstick.putDown();
            lock.unlock();
        }catch (Exception e){
            lock.unlock();
        }
    }

    @Override
    public void run() {
        for(;;){
            eat();
        }
    }
}
