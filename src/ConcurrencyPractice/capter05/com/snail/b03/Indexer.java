package ConcurrencyPractice.capter05.com.snail.b03;

import java.io.File;
import java.util.concurrent.BlockingDeque;

public class Indexer implements Runnable{

    private final BlockingDeque<File> blockingDeque;

    public Indexer(BlockingDeque<File> blockingDeque){
        this.blockingDeque = blockingDeque;
    }

    @Override
    public void run() {
        while(true){
            try {
                blockingDeque.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
