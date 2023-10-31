import java.util.List;

public class PrimeCheckerRunnable implements Runnable {
    private final int start;
    private final int end;
    protected List<Integer> primeNumbers;

    PrimeChecker primeChecker;

    public PrimeCheckerRunnable(int start, int end, List<Integer> primeNumbers, PrimeChecker primeChecker) {
        this.start = start;
        this.end = end;
        this.primeNumbers = primeNumbers;
        this.primeChecker = primeChecker;
    }

    @Override
    public void run() {
        for (int number = start; number <= end; number++) {
            if (primeChecker.isPrime(number)) {
                this.primeNumbers.add(number);
            }
        }
    }
}