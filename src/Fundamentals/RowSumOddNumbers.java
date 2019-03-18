package Fundamentals;

public class RowSumOddNumbers {

    public static int rowSumOddNumbers(int n) {
        int count = n * (n + 1) / 2;
        int start = 2 * (count - n + 1) - 1;
        int end = 2 * count - 1;
        int sum = 0;
        for (int i = start; i <= end; i += 2) {
            sum += i;
        }
        return sum;
    }
}
