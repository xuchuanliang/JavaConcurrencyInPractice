package ThreadPattenByPic.capter12.t3;

/**
 * @author xuchuanliangbt
 * @title: DisplayClientThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/148:29
 * @Version
 */
public class DisplayClientThread extends Thread{

    private ActiveObject activeObject;
    private String s;

    public DisplayClientThread(String name,ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
        this.s = name;
    }

    @Override
    public void run() {
        for(int i=0;true;i++){
            activeObject.displayString(Thread.currentThread().getName()+":"+i);
        }
    }
}
