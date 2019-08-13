package ThreadPattenByPic.capter12.t2;

/**
 * @author xuchuanliangbt
 * @title: ActiveObjectFactory
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1317:25
 * @Version
 */
public class ActiveObjectFactory {
    public static ActiveObject createActiveObjectFactory(){
        Servant servant = new Servant();
        ActivationQueue activationQueue = new ActivationQueue();
        ScheduleThread scheduleThread = new ScheduleThread(activationQueue);
        scheduleThread.start();
        Proxy proxy = new Proxy(scheduleThread,servant);
        return proxy;
    }
}
