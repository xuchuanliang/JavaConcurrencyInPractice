package ThreadPattenByPic.capter12.t1;

/**
 * @author xuchuanliangbt
 * @title: Proxy
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1218:10
 * @Version
 */
public class Proxy implements ActiveObject{
    private final ScheduleThread scheduleThread;
    private final Servant servant;

    public Proxy(ScheduleThread scheduleThread, Servant servant) {
        this.scheduleThread = scheduleThread;
        this.servant = servant;
    }

    @Override
    public Result<String> makeString(int count, char fillChar) {
        FutureResult futureResult = new FutureResult();
        scheduleThread.invoke(new MakeStringRequest(servant,futureResult,count,fillChar));
        return futureResult;
    }

    @Override
    public void displayString(String s) {
        scheduleThread.invoke(new TakeStringRequest(servant,s));
    }
}
