package ThreadPattenByPic.capter12.t1;

/**
 * @author xuchuanliangbt
 * @title: Main
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/1216:37
 * @Version
 */
public class Main {
    public static void main(String[] args){
        ActiveObject activeObject = ActivationObjectFactory.createActiveObject();
        new MakerClientThread("alice",activeObject).start();
        new MakerClientThread("bobby",activeObject).start();
        new DispalyClientThread("chris",activeObject).start();
    }
}
