package codewars;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

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

    // 复利原理
    public static void main(String[] args) {
        int n = 40;
        long base = 1;
        double rate = 2;
        ArrayList<Long> list = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            list.add(base);
            base = (long) Math.ceil(base * rate);
        }
        System.out.print("[");
        for (int i = 0; i < list.size(); ++i) {
            System.out.println("" + (i + 1) + ":" + list.get(i) + ",");
        }
        System.out.println("]");
    }

    public static String high(String s) {
        String[] words = s.split(" ");
        Integer[] scores = Arrays
                .stream(words)
                .parallel()
                .map(w -> w.chars().map(c -> c - 'a' + 1).sum())
                .toArray(Integer[]::new);
        Integer maxScore = Stream.of(scores).parallel().max(Integer::compareTo).orElse(0);
        for (int i = 0; i < scores.length; ++i) {
            if (scores[i].equals(maxScore)) return words[i];
        }
        return "";
    }

    public static boolean nameInStr(String str, String name) {
        return str.matches("." + name.replaceAll("(.)", "*$1.") + "*");
    }

    // Brainfuck Translator
    public static String translateToC(String input) {
        // "++++", "*p += 4;\n"
        // "----", "*p -= 4;\n"
        // ">>>>", "p += 4;\n"
        // "<<<<", "p -= 4;\n"
        // ".", "putchar(*p);\n"
        // ",", "*p = getchar();\n"
        // "[[[]]", "Error!"
        // "[][]", ""
        // "[.]", "if (*p) do {\n  putchar(*p);\n} while (*p);\n"

        // + -> *p += 1;
        // - -> *p -= 1;
        // > -> p += 1;
        // < -> p -= 1;
        // . -> putchar(*p);
        // , -> getchar(*p);
        // [ -> if (*p) do {\n
        // ] -> } while (*p);

        // +- -+ <> >< []

        // 1. 判断语法是否正确
        int left = 0;
        char[] chars = input.replaceAll("[^\\[\\]]", "").toCharArray();
        for (char aChar : chars) {
            if (aChar == '[') ++left;
            if (aChar == ']') --left;
        }
        if (left != 0) return "Error!";

        // 2.1. 移除所有无效字符
        String brainfuck = input.replaceAll("[^+\\-><.,\\[\\]]", "");
        // 2.2. 移除所有多余代码
        while (true) {
            String tmp = brainfuck.replaceAll("(\\+-|-\\+|<>|><|\\[])", "");
            if (tmp.length() == brainfuck.length()) {
                break;
            } else {
                brainfuck = tmp;
            }
        }

        // 3. 处理 + - > <
        // +
        Pattern p = Pattern.compile("(\\+{2,})");
        Matcher m = p.matcher(brainfuck);
        while (m.find()) {
            brainfuck = brainfuck.replaceFirst("\\+{2,}", String.format("+%d", m.group(0).length()));
            m = p.matcher(brainfuck);
        }
        // -
        p = Pattern.compile("(-{2,})");
        m = p.matcher(brainfuck);
        while (m.find()) {
            brainfuck = brainfuck.replaceFirst("-{2,}", String.format("-%d", m.group(0).length()));
            m = p.matcher(brainfuck);
        }
        // >
        p = Pattern.compile("(>{2,})");
        m = p.matcher(brainfuck);
        while (m.find()) {
            brainfuck = brainfuck.replaceFirst(">{2,}", String.format(">%d", m.group(0).length()));
            m = p.matcher(brainfuck);
        }
        // <
        p = Pattern.compile("(<{2,})");
        m = p.matcher(brainfuck);
        while (m.find()) {
            brainfuck = brainfuck.replaceFirst("<{2,}", String.format("<%d", m.group(0).length()));
            m = p.matcher(brainfuck);
        }

        // 4. 生成 C 代码

        return brainfuck;
    }
}
