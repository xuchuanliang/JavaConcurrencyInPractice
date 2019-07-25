package ConcurrencyPractice.capter05.com.snail.b03;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Test {

    public static void main(String[] args){
        Thread.currentThread().interrupt();
    }

    public static void startIndexing(File[] root){
        BlockingDeque<File> blockingDeque = new LinkedBlockingDeque<>();
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        };
        for(File file:root){
            new Thread(new FileCrawler(blockingDeque,fileFilter,file)).start();
        }
        for(int i=0; i<10; i++){
            new Thread(new Indexer(blockingDeque)).start();
        }
    }
}
