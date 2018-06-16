package capter06.com.snail.b1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模仿串行的处理任务
 */
public class SingleThreadWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true){
            Socket connect = serverSocket.accept();
            handle(connect);
        }
    }
    //处理任务
    public static void handle(Socket connect){

    }
}
