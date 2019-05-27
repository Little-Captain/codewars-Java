package codewars;

import java.util.Arrays;

public class Abbreviator {

    public static void main(String[] args) {
        System.out.println(new Abbreviator().abbreviate("doggy: cat_a: balloon'sits'is"));
    }

    public String abbreviate(String string) {
        return Arrays
                .stream(string.split("[^a-zA-Z]"))
                .filter(w -> w.length() >= 4)
                .reduce(string, (r, w) -> r.replaceFirst(w, w.replaceFirst(w.substring(1, w.length() - 1), "" + (w.length() - 2))));
    }
}
