package ThreadPattenByPic.capter06;

/**
 * @author xuchuanliangbt
 * @title: ReaderThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2917:42
 * @Version
 */
public class ReaderThread extends Thread{

    private final Data data;

    public ReaderThread(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        while (true){
            try {
                char[] read = data.read();
                System.out.println(Thread.currentThread().getName()+" reads "+String.valueOf(read));
            } catch (InterruptedException e) {
            }
        }
    }
}
