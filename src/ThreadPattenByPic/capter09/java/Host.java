package ThreadPattenByPic.capter09.java;

import ThreadPattenByPic.capter09.my.RealData;

import java.util.concurrent.Callable;

/**
 * @author xuchuanliangbt
 * @title: Host
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/119:02
 * @Version
 */
public class Host {
    public FutureData request(final int count,final char c){
        FutureData futureData = new FutureData(()->new RealData(count,c));
        new Thread(futureData).start();
        return futureData;
    }
}
