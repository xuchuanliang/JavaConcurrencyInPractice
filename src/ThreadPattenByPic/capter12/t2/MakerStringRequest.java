package ThreadPattenByPic.capter12.t2;

/**
 * @author xuchuanliangbt
 * @title: MakerStringRequest
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1316:17
 * @Version
 */
public class MakerStringRequest extends MethodRequest{
    private int count;
    private char c;
    public MakerStringRequest(ActiveObject readActiveObject, FutureResult<String> futureResult,int count,char c) {
        super(readActiveObject, futureResult);
        this.count = count;
        this.c = c;
    }

    @Override
    void execute() {
        Result<String> result = readActiveObject.makeString(count, c);
        futureResult.setRealResult(result);
    }
}
