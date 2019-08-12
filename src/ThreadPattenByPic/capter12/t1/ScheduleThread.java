package ThreadPattenByPic.capter12.t1;

/**
 * @author xuchuanliangbt
 * @title: ScheduleThread
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1218:09
 * @Version
 */
public class ScheduleThread extends Thread{
    private final ActivationQueue activationQueue;

    public ScheduleThread(ActivationQueue activationQueue){
        this.activationQueue = activationQueue;
    }

    public void invoke(MethodRequest request){
        activationQueue.putRequest(request);
    }

    @Override
    public void run() {
        while (true){
            activationQueue.takeRequest().execute();
        }
    }
}
