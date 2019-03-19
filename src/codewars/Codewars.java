package codewars;

public class Codewars {

    public static String oddOrEven(int[] array) {
        int sum = 0;
        for (int num: array) {
            sum += num;
        }
        return (sum % 2 == 0) ? "even" : "odd";
    }
}
