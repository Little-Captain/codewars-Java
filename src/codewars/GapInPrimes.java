package codewars;

public class GapInPrimes {

    public static long[] gap(int g, long m, long n) {
        long first = -1;
        long second = -1;
        for (long i = m; i <= n; ++i) {
            if (isPrime(i)) {
                if (first == -1) {
                    first = i;
                } else if (second == -1) {
                    second = i;
                } else {
                    first = second;
                    second = i;
                }
            }
            if (first != -1 && second != -1 && g == second - first) {
                break;
            }
        }
        if (first == -1 || second == -1 || g != second - first) return null;
        return new long[]{first, second};
    }

    private static boolean isPrime(long number) {
        for (int i = 2; i <= Math.sqrt(number); ++i) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
