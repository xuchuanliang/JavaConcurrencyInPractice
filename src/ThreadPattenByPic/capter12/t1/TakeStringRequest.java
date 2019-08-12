package ThreadPattenByPic.capter12.t1;

/**
 * @author xuchuanliangbt
 * @title: MakeStringRequest
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1217:42
 * @Version
 */
public class TakeStringRequest extends MethodRequest<String>{
    private String s;
    public TakeStringRequest(Servant servant, String s) {
        super(servant, null);
        this.s = s;
    }

    @Override
    public void execute() {
        servant.displayString(s);
    }
}
