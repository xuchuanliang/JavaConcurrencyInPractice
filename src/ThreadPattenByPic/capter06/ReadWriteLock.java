package ThreadPattenByPic.capter06;

/**
 * @author xuchuanliangbt
 * @title: ReadWriteLock
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2917:42
 * @Version
 */
public class ReadWriteLock {
    /**
     * 正在读的线程
     */
    private int readingReaders = 0;
    /**
     * 等待写的线程
     */
    private int waitingWriters = 0;
    /**
     * 正在写的线程
     */
    private int writingWriters = 0;
    /**
     * 是否写优先
     * 增加该标识的作用是能够增加读写线程执行的公平性
     * <strong></strong>
     */
    private boolean preferWriter = true;

    /**
     * 读加锁：有写入线程或者在写入优先的情况下有等待写入线程，则读线程等待，进入线程等待执行列表
     * <p>
     * 说明：本读写锁中如果没有是否优先写标识，那么正常逻辑是如下：
     *
     * @throws InterruptedException
     */
    public synchronized void readLock() throws InterruptedException {
        while (writingWriters > 0 || (preferWriter && waitingWriters > 0)) {
            wait();
        }
        readingReaders++;
    }

    /**
     * 读线程解锁，唤醒所有的等待线程
     * 此处当读线程解锁后，唤醒所有线程不是很理解
     * 个人理解的逻辑不应该是当读线程为0的时候才唤醒所有的等待线程么？因为此处等待线程只有写线程，因为读线程不会等待
     * 但是此处没有先判断readingReaders是否为0，在进行唤醒线程步骤的原因是因为如果读线程比较多，并且读线程不会阻塞那么将会导致
     * 读线程的数量不会递减到0，那么将会导致写线程没有执行的机会，失去了读写线程的意义，变成了只读线程
     * <strong>主要会导致读写线程之间的不公平性增加</strong>
     */
    public synchronized void readUnLock() {
        readingReaders--;
        preferWriter = true;
        notifyAll();
    }

    /**
     * 进入写线程方法，如果当前存在读线程或者存在写线程在工作，那么则等待，同时等待线程累加，此处细节：进入方法后先等待线程累加，
     * 其次进入等待，若通过等待，那么则等待线程递减，进行写线程操作
     *
     * @throws InterruptedException
     */
    public synchronized void writeLock() throws InterruptedException {
        waitingWriters++;
        try {
            while (readingReaders > 0 || writingWriters > 0) {
                wait();
            }
        } finally {
            waitingWriters--;
        }
        writingWriters++;
    }

    /**
     * 写线程解锁，数量递减，唤醒现在所有等待的读线程或者等待写线程
     * 此处写线程执行完后，writingWrites递减，并且写优先标识设置成false，并且唤醒所有线程，是为了给读线程执行的机会，否则若写
     * 线程数量比价多的情况下，等待写线程不能递归成0，则会一直执行写线程，读线程没有执行的机会
     * <strong>主要会导致读写线程之间的不公平性增加</strong>
     */
    public synchronized void writeUnLock() {
        writingWriters--;
        preferWriter = false;
        notifyAll();
    }
}
