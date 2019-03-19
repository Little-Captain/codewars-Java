package codewars;

import java.util.ArrayList;

public class Persist {

    public static int persistence(long n) {
        int count = 0;
        while (true) {
            // 获取所有的数字
            ArrayList<Long> digits = new ArrayList<>();
            long tmp = n;
            while (true) {
                digits.add(tmp % 10);
                tmp = tmp / 10;
                if (tmp == 0) {
                    break;
                }
            }
            if (digits.size() == 1) {
                break;
            }
            // 求积
            long product = 1;
            for (long d : digits) {
                product *= d;
            }
            if (product >= 10) {
                n = product;
                ++count;
            } else {
                ++count;
                break;
            }
        }
        return count;
    }
}
