package Fundamentals;

import java.util.ArrayList;
import java.util.HashMap;

public class EnoughIsEnough {

    public static int[] deleteNth(int[] elements, int maxOccurrences) {
        HashMap<Integer, Integer> elementCount = new HashMap<>();
        ArrayList<Integer> resultList = new ArrayList<>();

        for (int element: elements) {
            int count = elementCount.containsKey(element) ? elementCount.get(element) : 0;
            if (count < maxOccurrences) {
                resultList.add(element);
                elementCount.put(element, count + 1);
            }
        }

        int[] result = new int[resultList.size()];
        for (int i = 0; i < result.length; ++i) {
            result[i] = resultList.get(i);
        }
        return result;
    }
}
