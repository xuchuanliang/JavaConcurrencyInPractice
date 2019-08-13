package ThreadPattenByPic.capter12.t2;

/**
 * @author xuchuanliangbt
 * @title: RealResult
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1315:32
 * @Version
 */
public class RealResult<T> implements Result<T> {
    private final T t;

    public RealResult(T t) {
        this.t = t;
    }

    @Override
    public T getResult() {
        return t;
    }
}
