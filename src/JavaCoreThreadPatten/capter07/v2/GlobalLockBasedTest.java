package JavaCoreThreadPatten.capter07.v2;

/**
 * 粗粒度锁解决死锁问题：实际上大大降低了并发性，转变成了串行
 */
public class GlobalLockBasedTest extends AbstractTest{
    public GlobalLockBasedTest(int initNum) {
        super(initNum);
    }

    public static void main(String[] args) {
        AbstractTest abstractTest = new GlobalLockBasedTest(2);
        abstractTest.run();
    }

    @Override
    protected AbstractPhilosopher[] initPhilosopher() {
        GlobalLckBasedPhilosopher[] globalLckBasedPhilosophers = new GlobalLckBasedPhilosopher[initNum];
        for(int i=0;i<initNum;i++){
            globalLckBasedPhilosophers[i] = new GlobalLckBasedPhilosopher(i,chopsticks[i],chopsticks[(i+1)%initNum]);
        }
        return globalLckBasedPhilosophers;
    }
}

