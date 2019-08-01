package ThreadPattenByPic.capter09.java;

import ThreadPattenByPic.capter09.my.Data;
import ThreadPattenByPic.capter09.my.RealData;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author xuchuanliangbt
 * @title: FutureData
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/119:01
 * @Version
 */
public class FutureData extends FutureTask<RealData> implements Data {

    public FutureData(Callable<RealData> callable) {
        super(callable);
    }

    @Override
    public String getContent() {
        try {
            return super.get().getContent();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
