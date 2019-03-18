package Fundamentals;

public class ProdFib {

    public static long[] productFib(long prod) {
        long[] currentFibPair = {0, 1};
        while (true) {
            long sum = currentFibPair[0] * currentFibPair[1];
            if (sum >= prod) {
                return new long[]{currentFibPair[0], currentFibPair[1], sum == prod ? 1 : 0};
            } else {
                currentFibPair = new long[]{currentFibPair[1], currentFibPair[0] + currentFibPair[1]};
            }
        }
    }
}
