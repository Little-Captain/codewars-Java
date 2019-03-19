package codewars;

import java.util.ArrayList;
import java.util.List;

public class Josephus {

    public static <T> List<T> josephusPermutation(final List<T> items, final int k) {
        ArrayList<T> result = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<>();
        int firstIndex = 0;
        int count;
        while (items.size() > result.size()) {
            count = 0;
            for (int i = firstIndex; i < firstIndex + items.size() * k; ++i) {
                int index = i % items.size();
                if (!indexes.contains(index)) {
                    ++count;
                    if (count == k) {
                        result.add(items.get(index));
                        indexes.add(index);
                        firstIndex = i + 1;
                        break;
                    }
                }
            }
        }
        return result;
    }
}
