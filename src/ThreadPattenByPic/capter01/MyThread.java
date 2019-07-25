package ThreadPattenByPic.capter01;

/**
 * @author xuchuanliangbt
 * @title: MyThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/2414:21
 * @Version
 */
public class MyThread extends Thread{
    @Override
    public void run() {
        for(int i=0;i<1000;i++){
            System.out.print("苹果");
        }
    }
}
