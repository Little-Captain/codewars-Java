package codewars;

import org.junit.Test;
import sun.security.krb5.internal.tools.Ktab;

import static org.junit.Assert.*;

public class KataTests {

    @Test
    public void basicTests() {
        assertEquals(21, Kata.nextBiggerNumber(12));
        assertEquals(531, Kata.nextBiggerNumber(513));
        assertEquals(2071, Kata.nextBiggerNumber(2017));
        assertEquals(441, Kata.nextBiggerNumber(414));
        assertEquals(414, Kata.nextBiggerNumber(144));
        assertEquals(1234567908, Kata.nextBiggerNumber(1234567890));
    }

    @Test
    public void sampleTest() {
        assertTrue(Kata.nameInStr("Across the rivers", "chris"));
        assertFalse(Kata.nameInStr("Next to a lake", "chris"));
        assertFalse(Kata.nameInStr("Under a sea", "chris"));
        assertFalse(Kata.nameInStr("A crew that boards the ship", "chris"));
        assertFalse(Kata.nameInStr("A live son", "Allison"));
    }

    String[][] tests = {new String[]{"++++", "*p += 4;\n"},
            new String[]{"----", "*p -= 4;\n"},
            new String[]{">>>>", "p += 4;\n"},
            new String[]{"<<<<", "p -= 4;\n"},
            new String[]{".", "putchar(*p);\n"},
            new String[]{",", "*p = getchar();\n"},
            new String[]{"[[[]]", "Error!"},
            new String[]{"[][]", ""},
            new String[]{"[.]", "if (*p) do {\n  putchar(*p);\n} while (*p);\n"}};

    @Test
    public void sampleTests() {
        for (String[] actExp : tests)
            System.out.println(Kata.translateToC(actExp[0]));
//        assertEquals(actExp[1], Kata.translateToC(actExp[0]));
    }
}
