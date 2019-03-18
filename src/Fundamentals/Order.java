package Fundamentals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Order {

    public static String order(String words) {
        String[] wordArray = words.split(" ");
        Arrays.sort(wordArray, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Pattern p = Pattern.compile("(\\d+)");
                Matcher m1 = p.matcher(o1);
                Matcher m2 = p.matcher(o2);
                if (m1.find() && m2.find()) {
                    return Integer.parseInt(m1.group(1)) < Integer.parseInt(m2.group(1)) ? -1 : 1;
                } else {
                    return 0;
                }
            }
        });
        String result = "";
        for (String word: wordArray) {
            result += " " + word;
        }
        return result.trim();
    }
}
