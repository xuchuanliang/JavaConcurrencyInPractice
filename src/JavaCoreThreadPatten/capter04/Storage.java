package JavaCoreThreadPatten.capter04;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicLong;

public class Storage implements Closeable,AutoCloseable {

    private final RandomAccessFile storeFile;
    private final FileChannel storeChannel;
    protected  final AtomicLong totalWrites = new AtomicLong(0);

    public Storage(long fileSize,String fileShortName) throws IOException{
        String fullFileName = System.getProperty("java.io.tmpdir")+"/"+fileShortName;
        String localFileName;
        localFileName = createStoreFile(fileSize,fullFileName);
        storeFile = new RandomAccessFile(localFileName,"rw");
        storeChannel = storeFile.getChannel();
    }

    public long getTotalWrites(){
        return totalWrites.get();
    }

    /**
     * 将data中指定的数据写入文件
     * @param offset
     * @param byteBuf
     * @return
     */
    public int store(long offset, ByteBuffer byteBuf) throws IOException {
        int length;
        storeChannel.write(byteBuf,offset);
        length = byteBuf.limit();
        totalWrites.addAndGet(length);
        return length;
    }

    private String createStoreFile(final long fileSize, String fullFileName) throws FileNotFoundException {
        File file = new File(fullFileName);
        RandomAccessFile raf;
        raf = new RandomAccessFile(file,"rw");
        try {
            raf.setLength(fileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fullFileName;
    }


    @Override
    public synchronized void close() throws IOException {
        if(storeChannel.isOpen()){
            storeChannel.close();
            storeFile.close();
        }
    }
}
