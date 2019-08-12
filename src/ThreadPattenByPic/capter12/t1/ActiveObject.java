package ThreadPattenByPic.capter12.t1;

/**
 * @author xuchuanliangbt
 * @title: ActiveObject
 * @projectName JavaConcurrencyInPractice
 * @description: 定义主动对象的接口的接口
 * @date 2019/8/1217:17
 * @Version
 */
public interface ActiveObject {
    /**
     * 创建字符串
     * @param count
     * @param fillChar
     * @return
     */
    Result<String> makeString(int count,char fillChar);

    /**
     * 展示字符串
     * @param s
     */
    void displayString(String s);
}
