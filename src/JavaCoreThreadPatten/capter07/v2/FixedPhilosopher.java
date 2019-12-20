package JavaCoreThreadPatten.capter07.v2;

/**
 * 使用锁排序规避死锁：总是从小到大获取锁
 */
public class FixedPhilosopher extends AbstractPhilosopher{
    private Chopstick one;
    private Chopstick other;
    public FixedPhilosopher(int id, Chopstick left, Chopstick right) {
        super(id,left,right);
        if(System.identityHashCode(left) > System.identityHashCode(right)){
            Chopstick mid = left;
            one = right;
            other = mid;
        }else{
            one = left;
            other = right;
        }
    }

    @Override
    public void eat() {
        synchronized (one){
            System.out.println(id+"拿起第一根筷子："+one);
            one.pickUp();
            synchronized (other){
                System.out.println(id+"拿起另外一根筷子："+other+";开始吃饭啦。。");
                other.pickUp();
                doEat();
                other.putDown();
            }
            one.putDown();
        }
        think();
    }
}
