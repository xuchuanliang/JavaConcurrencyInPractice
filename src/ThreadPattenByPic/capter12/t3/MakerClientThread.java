package ThreadPattenByPic.capter12.t3;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author xuchuanliangbt
 * @title: MakerClientThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/148:26
 * @Version
 */
public class MakerClientThread extends Thread{

    private final ActiveObject activeObject;
    private final char fillChar;

    public MakerClientThread(String name,ActiveObject activeObject){
        super(name);
        this.activeObject = activeObject;
        fillChar = name.charAt(0);
    }

    @Override
    public void run() {
        for(int i=0;true;i++){
            Future<String> future = activeObject.makeString(i, fillChar);
            try {
                System.out.println("get String "+future.get());
            } catch (InterruptedException e) {
            } catch (ExecutionException e) {
            }
        }
    }
}
