package codewars;

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class SolutionTest {
    @Test
    public void validPins() {
        assertEquals(true, Solution.validatePin("1234"));
        assertEquals(true, Solution.validatePin("0000"));
        assertEquals(true, Solution.validatePin("1111"));
        assertEquals(true, Solution.validatePin("123456"));
        assertEquals(true, Solution.validatePin("098765"));
        assertEquals(true, Solution.validatePin("000000"));
        assertEquals(true, Solution.validatePin("090909"));
    }

    @Test
    public void nonDigitCharacters() {
        assertEquals(false, Solution.validatePin("a234"));
        assertEquals(false, Solution.validatePin(".234"));
    }

    @Test
    public void invalidLengths() {
        assertEquals(false, Solution.validatePin("1"));
        assertEquals(false, Solution.validatePin("12"));
        assertEquals(false, Solution.validatePin("123"));
        assertEquals(false, Solution.validatePin("12345"));
        assertEquals(false, Solution.validatePin("1234567"));
        assertEquals(false, Solution.validatePin("-1234"));
        assertEquals(false, Solution.validatePin("1.234"));
        assertEquals(false, Solution.validatePin("00000000"));
    }

    @Test
    public void basicTests() {
        assertEquals("Lew", Kata.declareWinner(new Fighter("Lew", 10, 2), new Fighter("Harry", 5, 4), "Lew"));
        assertEquals("Harry", Kata.declareWinner(new Fighter("Lew", 10, 2), new Fighter("Harry", 5, 4), "Harry"));
        assertEquals("Harald", Kata.declareWinner(new Fighter("Harald", 20, 5), new Fighter("Harry", 5, 4), "Harry"));
        assertEquals("Harald", Kata.declareWinner(new Fighter("Harald", 20, 5), new Fighter("Harry", 5, 4), "Harald"));
        assertEquals("Harald", Kata.declareWinner(new Fighter("Jerry", 30, 3), new Fighter("Harald", 20, 5), "Jerry"));
        assertEquals("Harald", Kata.declareWinner(new Fighter("Jerry", 30, 3), new Fighter("Harald", 20, 5), "Harald"));
    }

    @Test
    public void testNormalCondition() {
        assertEquals(9, Solution.solveSuperMarketQueue(new int[]{2, 2, 3, 3, 4, 4}, 2));
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, Solution.solveSuperMarketQueue(new int[]{}, 1));
    }

    @Test
    public void testSingleTillManyCustomers() {
        assertEquals(15, Solution.solveSuperMarketQueue(new int[]{1, 2, 3, 4, 5}, 1));
    }

    @Test
    public void testZeros() throws Exception {
        assertThat(Solution.zeros(0), is(0));
        assertThat(Solution.zeros(6), is(1));
        assertThat(Solution.zeros(14), is(2));
        assertThat(Solution.zeros(14000), is(3498));
    }

    @Test
    public void testBasic() {
        assertEquals("2-7-4", Solution.dashatize(274));
        assertEquals("5-3-1-1", Solution.dashatize(5311));
        assertEquals("86-3-20", Solution.dashatize(86320));
        assertEquals("9-7-4-3-02", Solution.dashatize(974302));
    }

    @Test
    public void testWeird() {
        assertEquals("0", Solution.dashatize(0));
        assertEquals("1", Solution.dashatize(-1));
        assertEquals("28-3-6-9", Solution.dashatize(-28369));
    }

    @Test
    public void testEdgeCases() {
        assertEquals("2-1-4-7-48-3-64-7", Solution.dashatize(Integer.MAX_VALUE));
        assertEquals("2-1-4-7-48-3-648", Solution.dashatize(Integer.MIN_VALUE));
        assertEquals("1-1-1-1-1-1-1-1-1-1", Solution.dashatize(-1111111111));
    }

    @Test
    public void testExamples() {
        shouldParse("#80FFA0",   new RGB(128, 255, 160));
        shouldParse("#3B7",      new RGB( 51, 187, 119));
    }

    /* Test helper functions */

    private void shouldParse(String color, RGB expected) {
        assertRgbEquals(color, expected, new HtmlColorParser(null).parse(color));
    }

    private static void assertRgbEquals(String input, RGB expected, RGB actual) throws AssertionError {
        try {
            System.out.printf("input: \"%s\"", input);
            assertEquals(expected, actual);
            System.out.println(" => pass!");
        } catch (AssertionError e) {
            String message = String.format(Locale.ENGLISH,
                    "expected: %s\nactual  : %s", expected, actual);
            throw new AssertionError(message, e);
        }
    }
}
