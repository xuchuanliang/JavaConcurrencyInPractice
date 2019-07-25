package ThreadPattenByPic.capter02;

import java.util.List;

/**
 * @author xuchuanliangbt
 * @title: WriterThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2415:55
 * @Version
 */
public class WriterThread extends Thread{
    private final List<Integer> list;

    public WriterThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run(){
        for(int i=0;true;i++){
            list.add(i);
            list.remove(0);
        }
    }
}
