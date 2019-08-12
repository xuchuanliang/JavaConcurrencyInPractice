package ThreadPattenByPic.capter12.t1;

/**
 * @author xuchuanliangbt
 * @title: FutureResult
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1217:20
 * @Version
 */
public class FutureResult<T> extends Result<T>{
    private Result<T> realResult;
    private boolean ready;
    public synchronized void setRealResult(Result<T> realResult){
        this.realResult = realResult;
        this.ready = true;
        notifyAll();
    }

    @Override
    public synchronized T getResultValue() {
        while (!ready){
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        return realResult.getResultValue();
    }
}
