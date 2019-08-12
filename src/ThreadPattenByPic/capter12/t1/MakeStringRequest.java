package ThreadPattenByPic.capter12.t1;

/**
 * @author xuchuanliangbt
 * @title: DisplayStringRequest
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1217:43
 * @Version
 */
public class MakeStringRequest extends MethodRequest<String>{
    private int count;
    private char fillChar;

    public MakeStringRequest(Servant servant, FutureResult<String> futureResult, int count, char fillChar) {
        super(servant, futureResult);
        this.count = count;
        this.fillChar = fillChar;
    }

    @Override
    public void execute() {
        Result<String> stringResult = servant.makeString(count, fillChar);
        futureResult.setRealResult(stringResult);
    }
}
