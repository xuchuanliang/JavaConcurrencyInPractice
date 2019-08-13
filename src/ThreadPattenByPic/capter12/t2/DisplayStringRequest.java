package ThreadPattenByPic.capter12.t2;

/**
 * @author xuchuanliangbt
 * @title: DisplayStringRequest
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1316:19
 * @Version
 */
public class DisplayStringRequest extends MethodRequest{
    private String s;
    public DisplayStringRequest(ActiveObject readActiveObject,String s) {
        super(readActiveObject, null);
        this.s = s;
    }

    @Override
    void execute() {
        readActiveObject.displayString(s);
    }
}
