package ThreadPattenByPic.capter12.t2;

/**
 * @author xuchuanliangbt
 * @title: Proxy
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1316:40
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
    public Result<String> makeString(int count, char c) {
        FutureResult<String> futureResult = new FutureResult<>();
        scheduleThread.invoke(new MakerStringRequest(servant,futureResult,count,c));
        return futureResult;
    }

    @Override
    public void displayString(String s) {
        scheduleThread.invoke(new DisplayStringRequest(servant,s));
    }
}
