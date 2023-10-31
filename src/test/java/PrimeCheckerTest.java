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
}
