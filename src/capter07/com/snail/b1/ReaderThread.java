package capter07.com.snail.b1;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 通过改写interrupt方法将非标准的取消操作封装在Thread中
 */
public class ReaderThread extends Thread{

    private final Socket socket;
    private final InputStream inputStream;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStream = socket.getInputStream();
    }

    /**
     * 通过关闭底层套接字和线程中断的方式自定义中断方法
     */
    @Override
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();

        }finally {
            super.interrupt();
        }
    }

    @Override
    public void run() {
        super.run();
    }
}
