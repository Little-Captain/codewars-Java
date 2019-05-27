package codewars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

// k-Primes
public class KPrimes {

    public static long[] countKprimes(int k, long start, long end) {
        final long[] primes = primes(0, end);
        LongStream
                .range(start, end + 1)
                .boxed()
                .map(n -> {
                    List<Long> factors = new ArrayList<>();
                    KPrimes.primeFactors(n, primes, factors);
                    return factors;
                })
                .filter(factors -> factors.size() == k)
                .forEach(factors -> {
                    System.out.println("----------------");
                    factors.forEach(System.out::println);
                });
        return null;
    }

    public static int puzzle(int s) {
        return 0;
    }

    private static boolean isPrime(long number) {
        for (int i = 2; i <= Math.sqrt(number); ++i) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static long[] primes(long start, long end) {
        return LongStream
                .range(start, end + 1)
                .filter(n -> n >= 2 && KPrimes.isPrime(n))
                .toArray();
    }

    private static void primeFactors(long number, long[] primes, List<Long> factors) {
        for (int i = 0; i < primes.length; ++i) {
            long prime = primes[i];
            if (prime >= number) break;
            if (number % prime == 0) {
                factors.add(prime);
                primeFactors(number / prime, primes, factors);
            }
        }
    }

    public static void main(String[] args) {
        long number = 9;

        final long[] primes = primes(0, number);
        Arrays.stream(primes).forEach(System.out::println);

        System.out.println("--------------------");

        List<Long> factors = new ArrayList<>();
        KPrimes.primeFactors(number, primes, factors);

        factors.forEach(System.out::println);
    }
}
