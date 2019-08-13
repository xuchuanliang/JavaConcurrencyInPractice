package ThreadPattenByPic.capter12.t2;

/**
 * @author xuchuanliangbt
 * @title: MakerClientThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1315:48
 * @Version
 */
public class MakerClientThread extends Thread{

    private ActiveObject activeObject;
    private char c;

    public MakerClientThread(String name,ActiveObject activeObject){
        super(name);
        this.activeObject = activeObject;
        this.c = name.charAt(0);
    }

    @Override
    public void run() {
        for(int i=0;true;i++){
            Result<String> result = activeObject.makeString(i, c);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            String result1 = result.getResult();
            System.out.println("make String :"+result1);
        }
    }
}
