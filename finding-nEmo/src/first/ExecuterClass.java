package first;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class ExecuterClass {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        for(int i = 0;i<20;i++) {
            System.out.println("RUN ::::::" + i);
            start(1,10240);
            start(2,10240);
            start(4,10240);
            start(6,10240);
            start(8,10240);
        }

    }

   static void start(int threads, int p) throws ExecutionException, InterruptedException {
        int scale = 1000;
        int numberOfThreads = threads;
        int numberOfIterations = p;
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        List<Future<BigDecimal>> futures = new ArrayList<>(numberOfThreads);
        int step = numberOfIterations / numberOfThreads;
        BigDecimal result = BigDecimal.ZERO;
        long startTime = System.currentTimeMillis();
        for (int j = 0; j <= numberOfIterations; j += step) {
            Future<BigDecimal> future = executor.submit(new EulerCalculator(j, j + step));
            futures.add(future);
        }
        for (Future<BigDecimal> future : futures) {
            result = result.add(future.get());
        }

        long duration = System.currentTimeMillis() - startTime;
        System.out.println(String.format("k: %d, threads: %d, duration: %d ms", numberOfIterations, numberOfThreads, duration));
    }

}
