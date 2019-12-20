package JavaCoreThreadPatten.capter07.v2;

/**
 * 通过按照锁的hashCode顺序来获取锁，解决死锁问题，因为都是从小到大获取锁，
 * 按照统一的顺序申请锁，消除循环等待资源问题
 */
public class FixedPhiosopherTest extends AbstractTest{
    public FixedPhiosopherTest(int initNum) {
        super(initNum);
    }

    public static void main(String[] args) {
        AbstractTest abstractTest = new FixedPhiosopherTest(2);
        abstractTest.run();
    }

    @Override
    protected AbstractPhilosopher[] initPhilosopher() {
        FixedPhilosopher[] fixedPhilosophers = new FixedPhilosopher[initNum];
        for(int i=0;i<initNum;i++){
            fixedPhilosophers[i] = new FixedPhilosopher(i,chopsticks[i],chopsticks[(i+1)%initNum]);
        }
        return fixedPhilosophers;
    }
}
