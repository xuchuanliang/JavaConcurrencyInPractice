package ThreadPattenByPic.capter12.t1;

/**
 * @author xuchuanliangbt
 * @title: MethodRequest
 * @projectName JavaConcurrencyInPractice
 * @description: 表示请求的抽象类
 * @date 2019/8/1217:39
 * @Version
 */
public abstract class MethodRequest<T> {
    protected final Servant servant;
    protected final FutureResult<T> futureResult;

    public MethodRequest(Servant servant, FutureResult<T> futureResult) {
        this.servant = servant;
        this.futureResult = futureResult;
    }

    /**
     * 执行方法
     */
    public abstract void execute();
}
