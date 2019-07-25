package ThreadPattenByPic.capter05;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author xuchuanliangbt
 * @title: Table2
 * @projectName JavaConcurrencyInPractice
 * @description:ArrayBlockingQueue实现消费者生产者模式
 * @date 2019/7/2516:54
 * @Version
 */
public class Table2 extends ArrayBlockingQueue<String> {

    public Table2(int count) {
        super(count);
    }

    @Override
    public void put(String cake) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+" puts "+cake);
        super.put(cake);
    }

    @Override
    public String take() throws InterruptedException {
        String cake = super.take();
        System.out.println(Thread.currentThread().getName()+" takes "+cake);
        return cake;
    }
}
