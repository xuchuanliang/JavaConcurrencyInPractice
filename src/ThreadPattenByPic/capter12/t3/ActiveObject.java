package ThreadPattenByPic.capter12.t3;

import java.util.concurrent.Future;

/**
 * @author xuchuanliangbt
 * @title: ActiveObject
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/148:19
 * @Version
 */
public abstract class ActiveObject {
    public abstract Future<String> makeString(int count,char fillChar);

    public abstract void displayString(String s);

    public abstract void shutdown();
}
