package ThreadPattenByPic.capter06;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xuchuanliangbt
 * @title: JavaReadWriteLock
 * @projectName JavaConcurrencyInPractice
 * @description:使用java读写锁实现Data
 * @date 2019/7/3017:32
 * @Version
 */
public class JavaReadWriteLockData {
    private final char[] chars;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    public JavaReadWriteLockData(int size){
        chars = new char[size];
        for(int i=0;i<size;i++){
            chars[i] = '*';
        }
    }

    /**
     * 读操作
     * @return
     * @throws InterruptedException
     */
    public char[] read(){
        readLock.lock();
        try{
            return doRead();
        }finally {
            readLock.unlock();
        }
    }

    /**
     * 写操作
     * @param c
     * @throws InterruptedException
     */
    public void write(char c){
        writeLock.lock();
        try{
            doWrite(c);
        }finally {
            writeLock.unlock();
        }
    }

    /**
     * 读操作
     * @return
     */
    private char[] doRead(){
        char[] newChar = new char[chars.length];
        for(int i=0;i<chars.length;i++){
            newChar[i] = chars[i];
        }
        slowly();
        return newChar;
    }

    /**
     * 写操作
     * @param c
     */
    private void doWrite(char c){
        for(int i=0;i<chars.length;i++){
            chars[i] = c;
            slowly();
        }
    }

    /**
     * 模拟读写耗时操作
     */
    private void slowly(){
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
