package ThreadPattenByPic.capter12.t2;

/**
 * @author xuchuanliangbt
 * @title: DisplayClientThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1315:52
 * @Version
 */
public class DisplayClientThread extends Thread{

    private ActiveObject activeObject;

    public DisplayClientThread(String name,ActiveObject activeObject){
        super(name);
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        for(int i=0;true;i++){
            activeObject.displayString(Thread.currentThread().getName()+":"+i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
        }
    }
}
