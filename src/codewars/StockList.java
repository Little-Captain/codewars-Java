package codewars;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StockList {

    // 1st parameter is the stocklist (L in example),
    // 2nd parameter is list of categories (M in example)
    public static String stockSummary(String[] stocklist, String[] categories) {
        if (stocklist.length == 0 || categories.length == 0) return "";
        return Arrays
                .stream(categories)
                .map(c -> "(" + c + " : " + Arrays
                        .stream(stocklist)
                        .filter(s -> s.startsWith(c))
                        .map(s -> Integer.parseInt(s.split(" ")[1]))
                        .reduce(0, Integer::sum) + ")")
                .collect(Collectors.joining(" - "));
    }
}
