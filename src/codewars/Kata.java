package codewars;

import java.util.ArrayList;

public class Kata {

    public static int opposite(int number) {
        return -number;
    }

    public static String declareWinner(Fighter fighter1, Fighter fighter2, String firstAttacker) {
        int f1WinCount = (fighter2.health - 1) / fighter1.damagePerAttack + 1;
        int f2WinCount = (fighter1.health - 1) / fighter2.damagePerAttack + 1;
        return (firstAttacker == fighter1.name) ?
                (f1WinCount <= f2WinCount ? fighter1.name : fighter2.name) :
                (f2WinCount <= f1WinCount ? fighter2.name : fighter1.name);
    }

    // TODO: 未通过测试
    public static long nextBiggerNumber(long n) {
        long[] digits = digits(n);
        int exchangeIndex1 = -1;
        int exchangeIndex2 = -1;
        outer:
        for (int i = 0; i < digits.length; ++i) {
            long exchangeDigit1 = digits[i];
            if (exchangeDigit1 == 0 || (i + 1 < digits.length && exchangeDigit1 == digits[i + 1])) {
                continue;
            }
            for (int j = i + 1; j < digits.length; ++j) {
                long exchangeDigit2 = digits[j];
                if (exchangeDigit2 < exchangeDigit1) {
                    exchangeIndex1 = i;
                    exchangeIndex2 = j;
                    break outer;
                }
            }
        }
        if (exchangeIndex1 == -1 || exchangeIndex2 == -1) {
            return -1;
        }
        long tmp = digits[exchangeIndex1];
        digits[exchangeIndex1] = digits[exchangeIndex2];
        digits[exchangeIndex2] = tmp;
        long result = 0;
        for (int i = digits.length - 1; i >= 0; --i) {
            result = 10 * result + digits[i];
        }
        return result;
    }

    private static long[] digits(long n) {
        ArrayList<Long> digits = new ArrayList<>();
        while (true) {
            long remainder = n % 10;
            n /= 10;
            digits.add(remainder);
            if (n == 0) {
                break;
            }
        }
        long[] result = new long[digits.size()];
        for (int i = 0; i < digits.size(); ++i) {
            result[i] = digits.get(i);
        }
        return result;
    }
}
