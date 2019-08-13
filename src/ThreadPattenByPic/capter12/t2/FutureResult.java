package ThreadPattenByPic.capter12.t2;

/**
 * @author xuchuanliangbt
 * @title: FutureResult
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1315:33
 * @Version
 */
public class FutureResult<T> implements Result<T>{

    private Result<T> realResult;
    private boolean ready = false;

    public synchronized void setRealResult(Result<T> realResult) {
        this.realResult = realResult;
        ready = true;
        notifyAll();
    }

    @Override
    public synchronized T getResult() {
        while (!ready){
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        return realResult.getResult();
    }
}
