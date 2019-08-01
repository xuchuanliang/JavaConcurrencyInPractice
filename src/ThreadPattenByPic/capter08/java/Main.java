package ThreadPattenByPic.capter08.java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuchuanliangbt
 * @title: Main
 * @projectName JavaConcurrencyInPractice
 * @description:
 * @date 2019/7/3117:52
 * @Version
 */
public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        new ClientThread("alice", executorService).start();
        new ClientThread("blob", executorService).start();
        new ClientThread("chris", executorService).start();
        new ClientThread("snail", executorService).start();
        new ClientThread("ant", executorService).start();
        new ClientThread("xuchuanliang", executorService).start();
    }
}
