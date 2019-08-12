package ThreadPattenByPic.capter12.t1;

/**
 * @author xuchuanliangbt
 * @title: RealResult
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1217:20
 * @Version
 */
public class RealResult<T> extends Result<T>{
    private final T result;

    public RealResult(T result) {
        this.result = result;
    }

    @Override
    T getResultValue() {
        return result;
    }
}
