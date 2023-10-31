import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class PrimeCheckerTest extends TestCase {
    PrimeChecker primeChecker = new PrimeChecker();

    public void testIsPrime() {
        System.out.println("13 is prime: " + primeChecker.isPrime(13));
        System.out.println("25 is prime: " + primeChecker.isPrime(25));
    }

    public void testRangeOfNumber() {
        for (int i = 1; i < 100; i++)
            System.out.println(i + " is prime: " + primeChecker.isPrime(i));
    }

    public void testRangeOfNumberUsingThread() throws InterruptedException {

        long startTime = System.currentTimeMillis();
        int start = 1;
        int end = 1000000;

        System.out.println("Thread Range:" + start + " ---> " + end);

        List<Integer> primes = new ArrayList<>();
        PrimeCheckerRunnable primeCheckerRunnable = new PrimeCheckerRunnable(start, end, primes, primeChecker);
        Thread thread = new Thread(primeCheckerRunnable);
        thread.start();
        thread.join();

        long endTime = System.currentTimeMillis() - startTime;

        System.out.println("\nTotal Time: " + endTime / 1000 + "s\nTotal Time: " + endTime + "ms");
    }

    public void testRangeOfNumberUsingMultiThread() throws InterruptedException {

        long startTime = System.currentTimeMillis();
        int start = 1;
        int end = 1000000;
        List<Integer> allPrimeNumbers = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        List<PrimeCheckerRunnable> list = new ArrayList<>();
        int threadCount = 4;
        int numbersPerThread = (end - start + 1) / threadCount;

        for (int i = 0; i < threadCount; i++) {
            int threadStart = start + i * numbersPerThread;
            int threadEnd = threadStart + numbersPerThread - 1;

            System.out.println("Thread" + (i + 1) + " Range:" + threadStart + " ---> " + threadEnd);

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

        long endTime = System.currentTimeMillis() - startTime;

        System.out.println("\nTotal Time: " + endTime / 1000 + "s\nTotal Time: " + endTime + "ms");
    }
}
