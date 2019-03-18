package Fundamentals;

import java.util.ArrayList;

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
}
