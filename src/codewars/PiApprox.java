package codewars;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class PiApprox {

    public static String iterPi2String(Double epsilon) {
        double pi = 4;
        long n = 1;
        while (Math.abs(pi - Math.PI) > epsilon) {
            pi += (double) ((n % 2 == 0) ? 4 : (-4)) / (1 + 2 * n);
            ++n;
        }
        return String.format("[%d, %.10f]", n, pi);
    }
}
