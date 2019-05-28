package codewars;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

// k-Primes
public class KPrimes {

    public static long[] countKprimes(int k, long start, long end) {
        return countKprimes(k, start, end, primes(0, end));
    }

    public static int puzzle(int s) {
        long[] primes = primes(0, s);
        long[] one = countKprimes(1, 0, s, primes);
        long[] three = countKprimes(3, 0, s, primes);
        long[] seven = countKprimes(7, 0, s, primes);
        List<long[]> combinations = new ArrayList<>();
        for (long se : seven) {
            for (long t : three) {
                for (long o : one) {
                    long[] combination = new long[3];
                    combination[0] = o;
                    combination[1] = t;
                    combination[2] = se;
                    combinations.add(combination);
                }
            }
        }
        return (int) combinations.stream()
                .filter(cs -> cs[0] + cs[1] + cs[2] == s)
                .peek(cs -> System.out.println("" + cs[0] + " " + cs[1] + " " + cs[2]))
                .count();
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

    private static long[] countKprimes(int k, long start, long end, long[] primes) {
        if (k == 1) return primes;
        return LongStream
                .range(start, end + 1)
                .boxed()
                .map(n -> {
                    long result = n;
                    List<Long> factors = new ArrayList<>();
                    while (!isPrime(result)) {
                        for (long p : primes) {
                            if (p > result) break;
                            if (result % p == 0) {
                                factors.add(p);
                                result = result / p;
                                break;
                            }
                        }
                    }
                    if (result != n) {
                        factors.add(result);
                    }
                    factors.add(0, n);
                    return factors;
                })
                .filter(factors -> factors.size() == k + 1)
                .map(l -> l.get(0))
                .mapToLong(Long::new)
                .toArray();
    }
}
