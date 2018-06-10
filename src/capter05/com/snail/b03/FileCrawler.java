package capter05.com.snail.b03;

import java.io.File;
import java.io.FileFilter;
import java.util.Objects;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class FileCrawler implements Runnable{

    private final BlockingDeque<File> fileQueue;
    private final FileFilter fileFilter;
    private final File root;

    public FileCrawler(BlockingDeque<File> fileQueue, FileFilter fileFilter, File root) {
        this.fileQueue = fileQueue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    @Override
    public void run() {
        try {
            crawl(root);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void crawl(File root) throws InterruptedException {
        File[] entries = root.listFiles(fileFilter);
        if(Objects.nonNull(entries)){
            for(File entry:entries){
                if(entry.isDirectory()){
                    crawl(entry);
                }else{
                    fileQueue.put(entry);
                }
            }
        }
    }
}
