package ThreadPattenByPic.capter12.t2;

/**
 * @author xuchuanliangbt
 * @title: Result
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1315:32
 * @Version
 */
public interface Result<T> {
    /**
     * 获取结果
     * @return
     */
    T getResult();
}
