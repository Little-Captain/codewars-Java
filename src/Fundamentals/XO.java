package Fundamentals;

public class XO {

    public static boolean getXO (String str) {
        str = str.toLowerCase();
        int length = str.length();
        int lengthRemoveX = str.replaceAll("x", "").length();
        int lengthRemoveO = str.replaceAll("o", "").length();
        return (length - lengthRemoveO) == (length - lengthRemoveX);
    }
}
