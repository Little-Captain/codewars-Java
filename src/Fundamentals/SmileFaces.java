package Fundamentals;
import java.util.*;

public class SmileFaces {

    public static int countSmileys(List<String> arr) {
        int smileCount = 0;
        for (String testing: arr) {
            smileCount += (testing.matches("[:;][-~]?[)D]")) ? 1 : 0;
        }
        return smileCount;
    }
}
