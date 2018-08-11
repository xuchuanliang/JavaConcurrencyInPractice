package capter07.com.snail.b2;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 通过改写interrupt方法将非标准的取消操作封装在Thread中
 */
public class ReaderThread extends Thread {

    private final Socket socket;
    private final InputStream inputStream;

    public ReaderThread(Socket socket,InputStream inputStream){
        this.socket = socket;
        this.inputStream = inputStream;
    }

    public void close(){
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
        try {
            byte[] bytes = new byte[1024];
            while (true){
                int count = inputStream.read(bytes);
                if(count<0){
                    break;
                }else if(count>0){
                    //处理
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
