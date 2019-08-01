package ThreadPattenByPic.capter09.my;

/**
 * @author xuchuanliangbt
 * @title: FutureData
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/116:46
 * @Version
 */
public class FutureData implements Data{
    private Data realData;
    private boolean ready = false;
    public synchronized void setRealData(Data realData){
        if(ready){
            return;
        }
        this.realData = realData;
        this.ready = true;
        notifyAll();
    }

    @Override
    public synchronized String getContent() {
        while (!ready){
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        return realData.getContent();
    }
}
