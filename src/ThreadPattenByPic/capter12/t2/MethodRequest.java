package ThreadPattenByPic.capter12.t2;

/**
 * @author xuchuanliangbt
 * @title: MethodRequest
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1315:57
 * @Version
 */
public abstract class MethodRequest {

    protected ActiveObject readActiveObject;
    protected FutureResult<String> futureResult;

    public MethodRequest(ActiveObject readActiveObject, FutureResult<String> futureResult) {
        this.readActiveObject = readActiveObject;
        this.futureResult = futureResult;
    }

    abstract void execute();
}
