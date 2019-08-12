package ThreadPattenByPic.capter12.t1;

/**
 * @author xuchuanliangbt
 * @title: DispalyClientThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1218:17
 * @Version
 */
public class DispalyClientThread extends Thread{
    private final ActiveObject activeObject;

    public DispalyClientThread(String name,ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        for(int i=0;true;i++){
            String s = Thread.currentThread().getName()+" "+i;
            activeObject.displayString(s);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
