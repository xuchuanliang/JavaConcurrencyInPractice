package JavaCoreThreadPatten.capter07.v2;

/**
 * 执行方法抽象函数
 */
public abstract class AbstractTest {
    protected final int initNum;
    protected final Chopstick[] chopsticks;
    protected final AbstractPhilosopher[] abstractPhilosophers;

    public AbstractTest(int initNum) {
        this.initNum = initNum;
        this.chopsticks = initChopstick();
        this.abstractPhilosophers = initPhilosopher();
    }

    /**
     * 初始化筷子
     * @return
     */
    protected Chopstick[] initChopstick(){
        Chopstick[] chopsticks = new Chopstick[initNum];
        for(int i=0;i<initNum;i++){
            chopsticks[i] = new Chopstick(i);
        }
        return chopsticks;
    }

    /**
     * 初始化哲学家，由子类进行实现
     * @return
     */
    protected abstract AbstractPhilosopher[] initPhilosopher();

    /**
     * 运行方法
     */
    protected final void run(){
        if(abstractPhilosophers==null || abstractPhilosophers.length<=0){
            System.err.println("初始化未完成");
        }
        for(AbstractPhilosopher abstractPhilosopher:abstractPhilosophers){
            abstractPhilosopher.start();
        }
    }
}
