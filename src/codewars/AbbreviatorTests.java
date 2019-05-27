package codewars;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AbbreviatorTests {

    private Abbreviator abbr = new Abbreviator();

    @Test
    public void testInternationalization() {
        assertEquals("i18n", abbr.abbreviate("internationalization"));
    }
}
