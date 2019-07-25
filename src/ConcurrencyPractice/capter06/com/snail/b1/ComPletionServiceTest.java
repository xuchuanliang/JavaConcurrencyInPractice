package ConcurrencyPractice.capter06.com.snail.b1;

import java.util.concurrent.*;

public class ComPletionServiceTest {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CompletionService completionService = new ExecutorCompletionService(executorService);
}
}
