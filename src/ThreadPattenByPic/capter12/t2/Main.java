package ThreadPattenByPic.capter12.t2;

/**
 * @author xuchuanliangbt
 * @title: Main
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1317:28
 * @Version
 */
public class Main {
    public static void main(String[] args){
        ActiveObject activeObject = ActiveObjectFactory.createActiveObjectFactory();
        new MakerClientThread("alice",activeObject).start();
        new MakerClientThread("bobby",activeObject).start();
        new DisplayClientThread("chris",activeObject).start();
    }
}
