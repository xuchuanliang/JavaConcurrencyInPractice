package second.capter03;

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
        RequestQueue requestQueue = new RequestQueue();
        for(int i=0;i<100;i++){
            new ServerThread(requestQueue, "Bob", 6535897L).start();
            new ClientThread(requestQueue, "ALICE", 3141592L).start();
        }
    }
}
