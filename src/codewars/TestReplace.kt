package codewars

import kotlin.test.assertEquals
import org.junit.Test

fun countRedBeads(nBlue: Int): Int {
    return if (nBlue < 2) 0 else (nBlue - 1) * 2
}

class TestReplace {

    @Test
    fun testFixed() {
        assertEquals(0, countRedBeads(0))
        assertEquals(0, countRedBeads(1))
        assertEquals(4, countRedBeads(3))
        assertEquals(8, countRedBeads(5))
    }

}
