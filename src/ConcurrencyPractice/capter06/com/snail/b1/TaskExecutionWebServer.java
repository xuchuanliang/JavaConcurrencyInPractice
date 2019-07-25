package ConcurrencyPractice.capter06.com.snail.b1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 基于线程池的web服务器
 */
public class TaskExecutionWebServer {
    //线程数量
    private static final int NTHREADS = 100;
    //初始化线程池
    private static final Executor threadPool = Executors.newFixedThreadPool(NTHREADS);
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true){
            final Socket connection = serverSocket.accept();
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    handle(connection);
                }
            };
            threadPool.execute(task);
        }
    }
    public static void handle(Socket connect){}
}
