package ThreadPattenByPic.capter12.t1;

/**
 * @author xuchuanliangbt
 * @title: ActivationObjectFactory
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1218:15
 * @Version
 */
public class ActivationObjectFactory {
    public static ActiveObject createActiveObject(){
        Servant servant = new Servant();
        ActivationQueue queue = new ActivationQueue();
        ScheduleThread scheduleThread = new ScheduleThread(queue);
        Proxy proxy = new Proxy(scheduleThread,servant);
        scheduleThread.start();
        return proxy;
    }
}
