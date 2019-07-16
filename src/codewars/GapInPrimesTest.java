package codewars;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class GapInPrimesTest {

    @Test
    public void testNormal() {
        Assert.assertEquals("[101, 103]", Arrays.toString(GapInPrimes.gap(2, 100, 110)));
        Assert.assertEquals("[103, 107]", Arrays.toString(GapInPrimes.gap(4, 100, 110)));
        Assert.assertEquals("[359, 367]", Arrays.toString(GapInPrimes.gap(8, 300, 400)));
        Assert.assertEquals("[337, 347]", Arrays.toString(GapInPrimes.gap(10, 300, 400)));
    }

    @Test
    public void testNull() {
        Assert.assertNull(GapInPrimes.gap(6, 100, 100));
        Assert.assertNull(GapInPrimes.gap(6, 100, 110));
    }
}
