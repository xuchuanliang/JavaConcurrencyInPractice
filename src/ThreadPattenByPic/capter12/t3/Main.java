package ThreadPattenByPic.capter12.t3;

/**
 * @author xuchuanliangbt
 * @title: Main
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/8/148:31
 * @Version
 */
public class Main {
    public static void main(String[] args){
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new MakerClientThread("ant",activeObject).start();
        new MakerClientThread("alice",activeObject).start();
        new DisplayClientThread("bolb",activeObject).start();
    }
}
