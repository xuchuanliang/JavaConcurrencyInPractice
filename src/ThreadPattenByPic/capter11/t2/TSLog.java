package ThreadPattenByPic.capter11.t2;

/**
 * @author xuchuanliangbt
 * @title: Log
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1212:21
 * @Version
 */
public class TSLog {
    private final String name;

    public TSLog(String name) {
        this.name = name;
    }

    public void log(String s){
        System.out.println(name+"==="+s);
    }
}
