package capter06.com.snail.b1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

/**
 * 在web服务器中为每个请求启动一个新的线程
 */
public class ThreadPerTaskWebServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true){
            final Socket connect = serverSocket.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    handle(connect);
                }
            });
        }
    }

    public static void handle(Socket connect){
    }
}
