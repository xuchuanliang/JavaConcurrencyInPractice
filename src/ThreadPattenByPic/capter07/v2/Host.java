package ThreadPattenByPic.capter07.v2;

import ThreadPattenByPic.capter07.v1.Helper;

import java.util.concurrent.ThreadFactory;

/**
 * @author xuchuanliangbt
 * @title: Host
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/318:21
 * @Version
 */
public class Host {
    private final ThreadFactory threadFactory;
    private final Helper helper = new Helper();

    public Host(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }

    public void request(final int count,final char c){
        System.out.println("request ("+count+","+c+")BEGIN");
        threadFactory.newThread(()->{
            helper.handle(count,c);
        });
        System.out.println("request ("+count+","+c+")END");
    }
}
