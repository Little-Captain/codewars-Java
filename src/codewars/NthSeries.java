package codewars;

public class NthSeries {

    public static void main(String[] args) {
        System.out.println(seriesSum(5));
    }

    public static String seriesSum(int n) {
        return String.format("%.2f", seriesSumOfDouble(n));
    }

    private static double seriesSumOfDouble(int n) {
        return (n == 0) ? 0 : (1.0 / (1 + 3 * (n - 1))) + seriesSumOfDouble(n - 1);
    }
}
