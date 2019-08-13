package ThreadPattenByPic.capter12.t2;

/**
 * @author xuchuanliangbt
 * @title: ActiveObject
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1315:39
 * @Version
 */
public interface ActiveObject {

    Result<String> makeString(int count,char c);

    void displayString(String s);
}
