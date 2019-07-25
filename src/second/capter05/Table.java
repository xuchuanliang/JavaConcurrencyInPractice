package second.capter05;

/**
 * @author xuchuanliangbt
 * @title: Table
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2515:42
 * @Version
 * 类似于Channel角色，用于在生产者和消费者之间传递数据，充当管道的作用，使用该中间角色可以实现先进先出（队列），先进后出（栈），优先的先传递（优先队列）
 */
public class Table {
    private final String[] buffer;
    /**
     * 下次put的位置
     */
    private int tail;
    /**
     * 下次take的位置
     */
    private int head;
    /**
     * buffer中的蛋糕数量
     */
    private int count;

    public Table(int count){
        buffer = new String[count];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }

    /**
     * 放蛋糕
     * @param cake
     * @throws InterruptedException
     */
    public synchronized void put(String cake) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+" puts "+cake);
        while (count >= buffer.length){
            wait();
        }
        buffer[tail] = cake;
        tail = (tail+1) % buffer.length;
        count++;
        notifyAll();
    }

    /**
     * 取蛋糕
     * @return
     * @throws InterruptedException
     */
    public synchronized String take() throws InterruptedException {
        while (count <= 0){
            wait();
        }
        String cake = buffer[head];
        head = (head+1) % buffer.length;
        count--;
        notifyAll();
        System.out.println(Thread.currentThread().getName()+" takes "+cake);
        return cake;
    }

}
