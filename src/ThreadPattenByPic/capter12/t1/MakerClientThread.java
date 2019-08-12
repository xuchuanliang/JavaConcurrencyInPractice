package ThreadPattenByPic.capter12.t1;

/**
 * @author xuchuanliangbt
 * @title: MakerClientThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1218:19
 * @Version
 */
public class MakerClientThread extends Thread{
    private final ActiveObject activeObject;
    private final char fillChar;

    public MakerClientThread(String name,ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
        this.fillChar = name.charAt(0);
    }

    @Override
    public void run() {
        for(int i=0;true;i++){
            Result<String> result = activeObject.makeString(i, fillChar);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            String value = result.getResultValue();
            System.out.println(Thread.currentThread().getName()+":value="+value);
        }
    }
}
