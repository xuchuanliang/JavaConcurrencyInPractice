package ThreadPattenByPic.capter12.t3;

/**
 * @author xuchuanliangbt
 * @title: ActiveObjectFactory
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/148:25
 * @Version
 */
public class ActiveObjectFactory{
    public static ActiveObject createActiveObject(){
        return new ActiveObjectImpl();
    }
}
