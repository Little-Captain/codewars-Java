package Fundamentals;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runners.JUnit4;

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
        assertEquals("Lew", Kata.declareWinner(new Fighter("Lew", 10, 2),new Fighter("Harry", 5, 4), "Lew"));
        assertEquals("Harry", Kata.declareWinner(new Fighter("Lew", 10, 2),new Fighter("Harry", 5, 4), "Harry"));
        assertEquals("Harald", Kata.declareWinner(new Fighter("Harald", 20, 5), new Fighter("Harry", 5, 4), "Harry"));
        assertEquals("Harald", Kata.declareWinner(new Fighter("Harald", 20, 5), new Fighter("Harry", 5, 4), "Harald"));
        assertEquals("Harald", Kata.declareWinner(new Fighter("Jerry", 30, 3), new Fighter("Harald", 20, 5), "Jerry"));
        assertEquals("Harald", Kata.declareWinner(new Fighter("Jerry", 30, 3), new Fighter("Harald", 20, 5), "Harald"));
    }

    @Test
    public void testNormalCondition() {
        assertEquals(9, Solution.solveSuperMarketQueue(new int[] { 2, 2, 3, 3, 4, 4 }, 2));
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, Solution.solveSuperMarketQueue(new int[] {}, 1));
    }

    @Test
    public void testSingleTillManyCustomers() {
        assertEquals(15, Solution.solveSuperMarketQueue(new int[] { 1, 2, 3, 4, 5 }, 1));
    }}
