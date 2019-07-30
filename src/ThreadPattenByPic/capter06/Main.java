package ThreadPattenByPic.capter06;

/**
 * @author xuchuanliangbt
 * @title: Main
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2917:42
 * @Version
 */
public class Main {
    public static void main(String[] args){
        Data data = new Data(10);
        new ReaderThread(data).start();
//        new ReaderThread(data).start();
//        new ReaderThread(data).start();
//        new ReaderThread(data).start();
//        new ReaderThread(data).start();
//        new ReaderThread(data).start();
        new WriteThread(data,"ABCDEFGHIJKLMNOPQRSTUVWXYZ").start();
        new WriteThread(data,"abcdefghijklmnopqrstuvwxyz").start();
        new WriteThread(data,"ABCDEFGHIJKLMNOPQRSTUVWXYZ").start();
        new WriteThread(data,"abcdefghijklmnopqrstuvwxyz").start();
        new WriteThread(data,"ABCDEFGHIJKLMNOPQRSTUVWXYZ").start();
        new WriteThread(data,"abcdefghijklmnopqrstuvwxyz").start();
        new WriteThread(data,"ABCDEFGHIJKLMNOPQRSTUVWXYZ").start();
        new WriteThread(data,"abcdefghijklmnopqrstuvwxyz").start();
        new WriteThread(data,"ABCDEFGHIJKLMNOPQRSTUVWXYZ").start();
        new WriteThread(data,"abcdefghijklmnopqrstuvwxyz").start();
        new WriteThread(data,"ABCDEFGHIJKLMNOPQRSTUVWXYZ").start();
        new WriteThread(data,"abcdefghijklmnopqrstuvwxyz").start();
        new WriteThread(data,"ABCDEFGHIJKLMNOPQRSTUVWXYZ").start();
        new WriteThread(data,"abcdefghijklmnopqrstuvwxyz").start();
    }
}
