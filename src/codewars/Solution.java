package codewars;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Solution {

    public static boolean validatePin(String pin) {
        return pin.matches("\\d{4}|\\d{6}");
    }

    public static int solveSuperMarketQueue(int[] customers, int n) {
        ArrayList<ArrayList<Integer>> queues = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            queues.add(new ArrayList<Integer>());
        }
        for (int customer : customers) {
            ArrayList<Integer> queue = getQueue(queues, true);
            queue.add(customer);
        }

        ArrayList<Integer> queue = getQueue(queues, false);
        int sum = 0;
        for (int time : queue) {
            sum += time;
        }

        return sum;
    }

    private static ArrayList<Integer> getQueue(ArrayList<ArrayList<Integer>> queues, boolean isMin) {
        ArrayList<Integer> queue = queues.get(0);
        int oldSum = 0;
        for (int time : queue) {
            oldSum += time;
        }
        for (int i = 1; i < queues.size(); ++i) {
            ArrayList<Integer> tmp = queues.get(i);
            int newSum = 0;
            for (int time : tmp) {
                newSum += time;
            }
            if (isMin && newSum < oldSum) {
                oldSum = newSum;
                queue = tmp;
            }
            if (!isMin && newSum > oldSum) {
                oldSum = newSum;
                queue = tmp;
            }
        }
        return queue;
    }

    public static String whoLikesIt(String... names) {
        String result;
        switch (names.length) {
            case 0:
                result = "no one likes this";
                break;
            case 1:
                result = names[0] + " likes this";
                break;
            case 2:
                result = names[0] + " and " + names[1] + " like this";
                break;
            case 3:
                result = names[0] + ", " + names[1] + " and " + names[2] + " like this";
                break;
            default:
                result = names[0] + ", " + names[1] + " and " + (names.length - 2) + " others like this";
                break;
        }
        return result;
    }

    public static int zeros(int n) {
        // 此算法基于
        // 1~n 有 n/2 个数能被2整除。所有数除以2以后，又有 1/2 个数能被2整除，也就是 n/4 个数能被2整除
        // 通过 n/2 + n/4 + n/8 + ... 能求出能被 2 整除多少次
        // 同理
        // 通过 n/5 + n/25 + n/125 + ... 能求出能被 5 整除多少次
        // 一个2，一个5配对确定能被10整除。由于2的个数远远大于5的个数。
        // 所以5的个数即是0的个数
        int countOf0 = 0;
        int divisor = 5;
        int count;
        while ((count = n / divisor) > 0) {
            countOf0 += count;
            divisor *= 5;
        }
        return countOf0;
    }

    public static String dashatize(int num) {
        return Integer
                .toString(num)
                .replaceAll("(1|3|5|7|9)", "-$1-")
                .replaceAll("--", "-")
                .replaceAll("^-|-$", "");
    }

}
