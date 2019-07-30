package ThreadPattenByPic.capter06;

/**
 * @author xuchuanliangbt
 * @title: Data
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2917:42
 * @Version
 */
public class Data {

    private final char[] chars;

    private final ReadWriteLock readWriteLock = new ReadWriteLock();

    public Data(int size){
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
    public char[] read() throws InterruptedException {
        readWriteLock.readLock();
        try{
           return doRead();
        }finally {
            readWriteLock.readUnLock();
        }
    }

    /**
     * 写操作
     * @param c
     * @throws InterruptedException
     */
    public void write(char c) throws InterruptedException {
        readWriteLock.writeLock();
        try{
            doWrite(c);
        }finally {
            readWriteLock.writeUnLock();
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
