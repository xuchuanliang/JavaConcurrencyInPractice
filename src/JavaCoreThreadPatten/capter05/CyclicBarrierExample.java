package JavaCoreThreadPatten.capter05;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 栅栏功能：模拟100个人爬山，爬山一共分为两段，第一段结束等人全到开始爬第二段，第二段结束全部到达后宣布胜利，团建结束
 */
public class CyclicBarrierExample {
    private static final CyclicBarrier CYCLIC_BARRIER = new CyclicBarrier(100);
    private static final CyclicBarrier CYCLIC_BARRIER2 = new CyclicBarrier(100);
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(new Person(i + "帅哥", CYCLIC_BARRIER)).start();
        }
    }

    static class Person implements Runnable {

        private final String name;
        private final CyclicBarrier cyclicBarrier;

        public Person(String name, CyclicBarrier cyclicBarrier) {
            this.name = name;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                //第一阶段爬山
                climb();
                //等别人都到达第一阶段终点
                //这里使用存在问题
                CYCLIC_BARRIER.await();
                //第二阶段爬山开始
                ATOMIC_INTEGER.set(2);
                climb();
                //等别人都到达第二阶段终点
                CYCLIC_BARRIER2.await();
                //所有人都到达终点，庆祝开始
                System.out.println("游戏结束，" + name + "打开了香槟！！！");
            } catch (InterruptedException e) {
            } catch (BrokenBarrierException e) {
            }
        }

        public void climb() {
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
                int s = new Random().nextInt(10);
                TimeUnit.SECONDS.sleep(s);
                System.out.println(name + "第" + ATOMIC_INTEGER.get() + "阶段爬山结束了");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


