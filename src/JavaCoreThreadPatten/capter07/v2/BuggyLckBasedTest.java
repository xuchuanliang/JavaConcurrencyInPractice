package JavaCoreThreadPatten.capter07.v2;

/**
 * 显示锁
 * Found one Java-level deadlock:
 * =============================
 * "Thread-1":
 *   waiting for ownable synchronizer 0x000000076bb34200, (a java.util.concurrent.locks.ReentrantLock$NonfairSync),
 *   which is held by "Thread-0"
 * "Thread-0":
 *   waiting for ownable synchronizer 0x000000076bb342a0, (a java.util.concurrent.locks.ReentrantLock$NonfairSync),
 *   which is held by "Thread-1"
 *
 * Java stack information for the threads listed above:
 * ===================================================
 * "Thread-1":
 *         at sun.misc.Unsafe.park(Native Method)
 *         - parking to wait for  <0x000000076bb34200> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
 *         at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
 *         at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(AbstractQueuedSynchronizer.java:836)
 *         at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(AbstractQueuedSynchronizer.java:870)
 *         at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(AbstractQueuedSynchronizer.java:1199)
 *         at java.util.concurrent.locks.ReentrantLock$NonfairSync.lock(ReentrantLock.java:209)
 *         at java.util.concurrent.locks.ReentrantLock.lock(ReentrantLock.java:285)
 *         at JavaCoreThreadPatten.capter07.v2.BuggyLckBasedPhilosopher.pickUpChopstick(BuggyLckBasedPhilosopher.java:38)
 *         at JavaCoreThreadPatten.capter07.v2.BuggyLckBasedPhilosopher.eat(BuggyLckBasedPhilosopher.java:27)
 *         at JavaCoreThreadPatten.capter07.v2.BuggyLckBasedPhilosopher.run(BuggyLckBasedPhilosopher.java:62)
 * "Thread-0":
 *         at sun.misc.Unsafe.park(Native Method)
 *         - parking to wait for  <0x000000076bb342a0> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)
 *         at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
 *         at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(AbstractQueuedSynchronizer.java:836)
 *         at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(AbstractQueuedSynchronizer.java:870)
 *         at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(AbstractQueuedSynchronizer.java:1199)
 *         at java.util.concurrent.locks.ReentrantLock$NonfairSync.lock(ReentrantLock.java:209)
 *         at java.util.concurrent.locks.ReentrantLock.lock(ReentrantLock.java:285)
 *         at JavaCoreThreadPatten.capter07.v2.BuggyLckBasedPhilosopher.pickUpChopstick(BuggyLckBasedPhilosopher.java:38)
 *         at JavaCoreThreadPatten.capter07.v2.BuggyLckBasedPhilosopher.eat(BuggyLckBasedPhilosopher.java:27)
 *         at JavaCoreThreadPatten.capter07.v2.BuggyLckBasedPhilosopher.run(BuggyLckBasedPhilosopher.java:62)
 *
 * Found 1 deadlock.
 * 出现死锁
 */
public class BuggyLckBasedTest extends AbstractTest{

    public BuggyLckBasedTest(int initNum) {
        super(initNum);
    }

    public static void main(String[] args) {
        AbstractTest abstractTest = new BuggyLckBasedTest(2);
        abstractTest.run();
    }

    @Override
    protected AbstractPhilosopher[] initPhilosopher() {
        BuggyLckBasedPhilosopher[] buggyLckBasedPhilosophers = new BuggyLckBasedPhilosopher[initNum];
        for(int i=0;i<initNum;i++){
            buggyLckBasedPhilosophers[i] = new BuggyLckBasedPhilosopher(i,chopsticks[i],chopsticks[(i+1)%initNum]);
        }
        return buggyLckBasedPhilosophers;
    }
}
