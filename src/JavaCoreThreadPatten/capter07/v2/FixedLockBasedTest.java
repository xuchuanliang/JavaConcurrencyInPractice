package JavaCoreThreadPatten.capter07.v2;

/**
 * 使用ReentrantLock.tryLock(timeout,timeUnit)来解决死锁问题
 */
public class FixedLockBasedTest extends AbstractTest{
    public FixedLockBasedTest(int initNum) {
        super(initNum);
    }

    public static void main(String[] args) {
        AbstractTest abstractTest = new FixedLockBasedTest(2);
        abstractTest.run();
    }

    @Override
    protected AbstractPhilosopher[] initPhilosopher() {
        FixedLockBasedPhiosopher[] fixedLockBasedPhiosophers = new FixedLockBasedPhiosopher[initNum];
        for(int i=0;i<initNum;i++){
            fixedLockBasedPhiosophers[i] = new FixedLockBasedPhiosopher(i,chopsticks[i],chopsticks[(i+1)%initNum]);
        }
        return fixedLockBasedPhiosophers;
    }
}
