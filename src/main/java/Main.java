import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int start = 1;
        int end = 100000000;
        PrimeChecker primeChecker = new PrimeChecker();
        List<Integer> allPrimeNumbers = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        List<PrimeCheckerRunnable> list = new ArrayList<>();
        int threadCount =10;
        int numbersPerThread = (end - start + 1) / threadCount;

        for (int i = 0; i < threadCount; i++) {
            int threadStart = start + i * numbersPerThread;
            int threadEnd = threadStart + numbersPerThread - 1;

            System.out.println("Thread" + (i+1) + " Range:" + threadStart + " ---> " + threadEnd);

            if (i == threadCount - 1) {
                threadEnd = end;
            }
            List<Integer> primes = new ArrayList<>();
            list.add(new PrimeCheckerRunnable(threadStart, threadEnd, primes, primeChecker));
            Thread thread = new Thread(list.get(i));
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        for (PrimeCheckerRunnable p : list) {
            allPrimeNumbers.addAll(p.primeNumbers);
        }

//        System.out.println(allPrimeNumbers);

        long endTime = System.currentTimeMillis() - startTime;

        System.out.println("\nTotal Time: "+ endTime/1000 +"s\nTotal Time: "+ endTime +"ms");
    }
}