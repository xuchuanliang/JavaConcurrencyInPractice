package ThreadPattenByPic.capter08.my;

/**
 * @author xuchuanliangbt
 * @title: Main
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/3117:52
 * @Version
 */
public class Main {
    public static void main(String[] args){
        Channel channel = new Channel(5);
        channel.startWork();
        new ClientThread("alice",channel).start();
        new ClientThread("blob",channel).start();
        new ClientThread("chris",channel).start();
        new ClientThread("snail",channel).start();
        new ClientThread("ant",channel).start();
        new ClientThread("xuchuanliang",channel).start();
    }
}
