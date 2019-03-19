package codewars;

public class LongestConsec {

    public static String longestConsec(String[] strarr, int k) {
        int n = strarr.length;
        if (n == 0 || k > n || k <= 0) {
            return "";
        }
        int[] lengths = new int[n];
        for (int i = 0; i < n; ++i) {
            lengths[i] = strarr[i].length();
        }
        int maxIndex = 0;
        int maxLength = 0;
        for (int i = 0, j = i + k - 1; j < n; ++i, ++j) {
            int sumOfLength = 0;
            for (int l = i; l <= j; ++l) {
                sumOfLength += lengths[l];
            }
            if (sumOfLength > maxLength) {
                maxLength = sumOfLength;
                maxIndex = i;
            }
        }
        String result = "";
        for (int i = maxIndex; i < maxIndex + k; ++i) {
            result += strarr[i];
        }
        return result;
    }
}
