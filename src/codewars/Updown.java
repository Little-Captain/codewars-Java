package codewars;

public class Updown {

    public static String arrange(String string) {
        string = string.toLowerCase();
        String[] strings = string.split(" ");
        if (strings.length == 0) return null;
        if (strings.length == 1) return strings[0];
        for (int i = 1; i < strings.length; i += 2) {
            if (strings[i].length() < strings[i - 1].length()) {
                swap(strings, i, i - 1);
            }
            if (i + 1 < strings.length && strings[i + 1].length() > strings[i].length()) {
                swap(strings, i, i + 1);
            }
            strings[i] = strings[i].toUpperCase();
        }
        return String.join(" ", strings);
    }

    private static void swap(String[] strings, int index1, int index2) {
        String tmp = strings[index1];
        strings[index1] = strings[index2];
        strings[index2] = tmp;
    }
}
