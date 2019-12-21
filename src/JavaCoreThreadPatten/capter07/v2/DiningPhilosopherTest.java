package JavaCoreThreadPatten.capter07.v2;

import JavaCoreThreadPatten.capter07.DeadlockDetector;

import java.util.List;

/**
 * 哲学家死锁问题模拟程序
 * Java stack information for the threads listed above:
 * ===================================================
 * "Thread-1":
 *         at JavaCoreThreadPatten.capter07.v2.DeadlockingPhilosopher.eat(DeadlockingPhilosopher.java:21)
 *         - waiting to lock <0x000000076bb24918> (a JavaCoreThreadPatten.capter07.v2.Chopstick)
 *         - locked <0x000000076bb28978> (a JavaCoreThreadPatten.capter07.v2.Chopstick)
 *         at JavaCoreThreadPatten.capter07.v2.AbstractPhilosopher.run(AbstractPhilosopher.java:35)
 * "Thread-0":
 *         at JavaCoreThreadPatten.capter07.v2.DeadlockingPhilosopher.eat(DeadlockingPhilosopher.java:21)
 *         - waiting to lock <0x000000076bb28978> (a JavaCoreThreadPatten.capter07.v2.Chopstick)
 *         - locked <0x000000076bb24918> (a JavaCoreThreadPatten.capter07.v2.Chopstick)
 *         at JavaCoreThreadPatten.capter07.v2.AbstractPhilosopher.run(AbstractPhilosopher.java:35)
 *
 * Found 1 deadlock.
 *
 * 我们可以发现一个死锁问题
 */
public class DiningPhilosopherTest extends AbstractTest{
    public DiningPhilosopherTest(int initNum) {
        super(initNum);
    }

    public static void main(String[] args) {
        //检测死锁服务
        new DeadlockDetector(2).start();
        AbstractTest abstractTest = new DiningPhilosopherTest(2);
        abstractTest.run();
    }

    @Override
    protected AbstractPhilosopher[] initPhilosopher() {
        DeadlockingPhilosopher[] deadlockingPhilosophers = new DeadlockingPhilosopher[initNum];
        for(int i=0;i<initNum;i++){
            deadlockingPhilosophers[i] = new DeadlockingPhilosopher(i,chopsticks[i],chopsticks[(i+1)%initNum]);
        }
        return deadlockingPhilosophers;
    }
}
