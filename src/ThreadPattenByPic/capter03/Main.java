package ThreadPattenByPic.capter03;

/**
 * @author xuchuanliangbt
 * @title: Main
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/259:59
 * @Version
 */
public class Main {
    public static void main(String[] args) {
//        RequestQueue requestQueue = new RequestQueue();
        RequestQueue2 requestQueue = new RequestQueue2();
        new ServerThread2(requestQueue, "Bob", 6535897L).start();
        new ClientThread2(requestQueue, "ALICE", 3141592L).start();
    }
}
