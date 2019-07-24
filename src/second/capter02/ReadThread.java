package second.capter02;

import java.util.List;

/**
 * @author xuchuanliangbt
 * @title: ReadThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2415:56
 * @Version
 */
public class ReadThread extends Thread {
    private final List<Integer> list;

    public ReadThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run(){
        while (true){
            synchronized (list){
                for(int n:list){
                    System.out.println(n);
                }
            }
        }
    }
}
