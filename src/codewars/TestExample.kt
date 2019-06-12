package codewars

import org.junit.Assert.assertArrayEquals
import kotlin.test.assertEquals
import org.junit.Test
import java.math.BigInteger
import kotlin.math.pow
import kotlin.math.sqrt

fun numberToString(num: Int) = "$num"

fun findSmallestInt(nums: List<Int>) = nums.min()!!

fun noSpace(x: String) = x.replace(" ", "")

fun maps(x: IntArray) = x.map { it * 2 }.toIntArray()

fun reverseLetter(str: String) = str.replace(Regex("[^a-zA-Z]"), "").reversed()

fun evenOrOdd(number: Int) = if (number % 2 == 0) "Even" else "Odd"

fun highAndLow(numbers: String): String {
    val nums = numbers.split(Regex("\\s+")).map { it.toInt() }
    return "${nums.max()!!} ${nums.min()!!}"
}

fun makeComplement(dna: String): String {
    return dna
        .map {
            when (it) {
                'A' -> 'T'
                'T' -> 'A'
                'C' -> 'G'
                else -> 'C'
            }
        }
        .joinToString("")
}

fun digPow(n: Int, p: Int) =
    n
        .toString()
        .mapIndexed { i, c -> c.toString().toDouble().pow(p + i).toLong() }
        .sum()
        .let { if (it % n.toLong() == 0L) (it / n.toLong()).toInt() else -1 }

fun findShort(s: String) = s.split(Regex("\\s+")).minBy(String::length)!!.length

fun spinWords(sentence: String) =
    sentence
        .split(" ")
        .joinToString(" ") { if (it.length >= 5) it.reversed() else it }

fun lastDigit(base: BigInteger, exponent: BigInteger): Int {
    if (exponent.compareTo(BigInteger.ZERO) == 0) return 1
    val map = mapOf(
        2 to arrayOf(2, 4, 8, 6), 3 to arrayOf(3, 9, 7, 1), 4 to arrayOf(4, 6),
        7 to arrayOf(7, 9, 3, 1), 8 to arrayOf(8, 4, 2, 6), 9 to arrayOf(9, 1)
    )
    return when (val num = base.toString().last().toString().toInt()) {
        0, 1, 5, 6 -> num
        in map.keys -> {
            val values = map.getValue(num)
            val valuesCount = values.size
            values[when (val rem = exponent.rem(BigInteger.valueOf(valuesCount.toLong())).toInt()) {
                in 0 until valuesCount -> (rem + valuesCount - 1) % valuesCount
                else -> error("")
            }]
        }
        else -> error("")
    }
}

fun findEvenIndex(arr: IntArray): Int {
    for (i in arr.indices) {
        if (arr.sliceArray(0 until i).sum() == arr.sliceArray(i + 1 until arr.size).sum()) return i
    }
    return if (arr.isEmpty()) 0 else -1
}

fun alphaSeq(str: String) =
    str
        .map(Char::toUpperCase)
        .sorted()
        .joinToString(",") { c -> c + c.toLowerCase().let { l -> String(CharArray(l - 'a') { l }) } }

fun predictAge(vararg ages: Int) = (sqrt(ages.sumByDouble { 1.0 * it * it }) * 0.5).toInt()

fun seven(n: Long): LongArray {
    var result = n
    var count = 0L
    while (result > 99) {
        ++count
        result = result / 10 - result % 10 * 2
    }
    return longArrayOf(result, count)
}

// 递归实现
fun seven1(n: Long, c: Long = 0): LongArray = if (n > 99) seven1(n / 10 - n % 10 * 2, c + 1) else longArrayOf(n, c)

class TestExample {

    @Test
    fun test1() {
        assertEquals("TTTT", makeComplement("AAAA"));
    }

    @Test
    fun test2() {
        assertEquals("TAACG", makeComplement("ATTGC"));
    }

    @Test
    fun test3() {
        assertEquals("emocleW", spinWords("Welcome"))
        assertEquals("Hey wollef sroirraw", spinWords("Hey fellow warriors"))
        assertEquals("This is a test", spinWords("This is a test"))
        assertEquals("This is rehtona test", spinWords("This is another test"))
        assertEquals("You are tsomla to the last test", spinWords("You are almost to the last test"))
        assertEquals("Just gniddik ereht is llits one more", spinWords("Just kidding there is still one more"))
    }

    @Test
    fun test4() {
        assertEquals(0, findEvenIndex(intArrayOf()))
        assertEquals(3, findEvenIndex(intArrayOf(1, 2, 3, 4, 3, 2, 1)))
        assertEquals(1, findEvenIndex(intArrayOf(1, 100, 50, -51, 1, 1)))
        assertEquals(-1, findEvenIndex(intArrayOf(1, 2, 3, 4, 5, 6)))
        assertEquals(3, findEvenIndex(intArrayOf(20, 10, 30, 10, 10, 15, 35)))
        assertEquals(-1, findEvenIndex(intArrayOf(-8505, -5130, 1926, -9026)))
        assertEquals(1, findEvenIndex(intArrayOf(2824, 1774, -1490, -9084, -9696, 23094)))
        assertEquals(6, findEvenIndex(intArrayOf(4, 5, 6, 7, 8, 9, 10, 9, 8, 7, 6, 5, 4)))
    }

    @Test
    fun test5() {
        println("Basic Tests")
        assertArrayEquals(longArrayOf(28, 7), seven1(477557101))
        assertArrayEquals(longArrayOf(47, 7), seven1(477557102))
        assertArrayEquals(longArrayOf(35, 1), seven1(371))
        assertArrayEquals(longArrayOf(7, 2), seven1(1603))
    }

    @Test
    fun testFixed1() {
        assertEquals("67", numberToString(67))
        assertEquals("123", numberToString(123))
        assertEquals("999", numberToString(999))
    }

    @Test
    fun testFixed2() {
        assertEquals("8j8mBliB8gimjB8B8jlB", noSpace("8 j 8   mBliB8g  imjB8B8  jl  B"))
        assertEquals("88Bifk8hB8BB8BBBB888chl8BhBfd", noSpace("8 8 Bi fk8h B 8 BB8B B B  B888 c hl8 BhB fd"))
        assertEquals("8aaaaaddddr", noSpace("8aaaaa dddd r     "))
    }

    @Test
    fun testFixed3() {
        assertEquals("Even", evenOrOdd(2))
        assertEquals("Even", evenOrOdd(0))
        assertEquals("Odd", evenOrOdd(7))
        assertEquals("Odd", evenOrOdd(1))
    }

    @Test
    fun testFixed4() {
        assertEquals("42 -9", highAndLow("8 3 -5 42 -1 0 0 -9 4 7 4 -4"))
    }

    @Test
    fun testFixed5() {
        assertEquals(1, digPow(89, 1))
        assertEquals(-1, digPow(92, 1))
        assertEquals(51, digPow(46288, 3))
    }

    @Test
    fun testFixed6() {
        assertEquals(3, findShort("bitcoin take over the world maybe who knows perhaps"))
        assertEquals(3, findShort("turns out random test cases are easier than writing out basic ones"))
    }

    @Test
    fun testFixed7() {
        assertEquals(
            "Eeeee,Ggggggg,Llllllllllll,Nnnnnnnnnnnnnn,Nnnnnnnnnnnnnn,Pppppppppppppppp,Qqqqqqqqqqqqqqqqq,Rrrrrrrrrrrrrrrrrr,Uuuuuuuuuuuuuuuuuuuuu,Xxxxxxxxxxxxxxxxxxxxxxxx,Zzzzzzzzzzzzzzzzzzzzzzzzzz",
            alphaSeq("ZpglnRxqenU")
        )
        assertEquals(
            "Bb,Eeeee,Ffffff,Ffffff,Ggggggg,Llllllllllll,Nnnnnnnnnnnnnn,Sssssssssssssssssss,Yyyyyyyyyyyyyyyyyyyyyyyyy,Yyyyyyyyyyyyyyyyyyyyyyyyy,Yyyyyyyyyyyyyyyyyyyyyyyyy",
            alphaSeq("NyffsGeyylB")
        )
        assertEquals(
            "Bb,Jjjjjjjjjj,Kkkkkkkkkkk,Mmmmmmmmmmmmm,Ooooooooooooooo,Qqqqqqqqqqqqqqqqq,Rrrrrrrrrrrrrrrrrr,Tttttttttttttttttttt,Uuuuuuuuuuuuuuuuuuuuu,Uuuuuuuuuuuuuuuuuuuuu,Vvvvvvvvvvvvvvvvvvvvvv",
            alphaSeq("MjtkuBovqrU")
        )
        assertEquals(
            "Dddd,Eeeee,Iiiiiiiii,Jjjjjjjjjj,Kkkkkkkkkkk,Mmmmmmmmmmmmm,Mmmmmmmmmmmmm,Nnnnnnnnnnnnnn,Ooooooooooooooo,Uuuuuuuuuuuuuuuuuuuuu,Vvvvvvvvvvvvvvvvvvvvvv",
            alphaSeq("EvidjUnokmM")
        )
        assertEquals(
            "Bb,Bb,Ccc,Ccc,Dddd,Eeeee,Hhhhhhhh,Iiiiiiiii,Nnnnnnnnnnnnnn,Vvvvvvvvvvvvvvvvvvvvvv,Xxxxxxxxxxxxxxxxxxxxxxxx",
            alphaSeq("HbideVbxncC")
        )
    }

    @Test
    fun exampleTests() {
        assertEquals(findSmallestInt(listOf(15, 20, 10, 17, 22, 9001)), 10)
    }

    @Test
    fun `Basic Tests 1`() {
        assertArrayEquals(intArrayOf(2, 4, 6), maps(intArrayOf(1, 2, 3)))
        assertArrayEquals(intArrayOf(8, 2, 2, 2, 8), maps(intArrayOf(4, 1, 1, 1, 4)))
        assertArrayEquals(intArrayOf(4, 4, 4, 4, 4, 4), maps(intArrayOf(2, 2, 2, 2, 2, 2)))
    }

    @Test
    fun `Basic Tests 2`() {
        val str = "krishan"
        assertEquals("nahsirk", reverseLetter("krishan"))
        assertEquals("nortlu", reverseLetter("ultr53o?n"))
        assertEquals("cba", reverseLetter("ab23c"))
        assertEquals("nahsirk", reverseLetter("krish21an"))
    }

    @Test
    fun `Basic Tests 3`() {
        assertEquals(4, lastDigit(BigInteger("4"), BigInteger("1")))
        assertEquals(6, lastDigit(BigInteger("4"), BigInteger("2")))
        assertEquals(9, lastDigit(BigInteger("9"), BigInteger("7")))
        assertEquals(0, lastDigit(BigInteger("10"), BigInteger("10000000000")))
        assertEquals(1, lastDigit(BigInteger("10"), BigInteger("0")))
        assertEquals(
            1,
            lastDigit(BigInteger("9435756757744477447576867898089079079808908347583277453475"), BigInteger("0"))
        )
        assertEquals(
            6,
            lastDigit(
                BigInteger("1606938044258990275541962092341162602522202993782792835301376"),
                BigInteger("2037035976334486086268445688409378161051468393665936250636140449354381299763336706183397376")
            )
        )
        assertEquals(
            7,
            lastDigit(
                BigInteger("3715290469715693021198967285016729344580685479654510946723"),
                BigInteger("68819615221552997273737174557165657483427362207517952651")
            )
        )
    }

    @org.junit.Test
    @Throws(Exception::class)
    fun basicTest() {
        assertEquals(86, predictAge(65, 60, 75, 55, 60, 63, 64, 45))
        assertEquals(79, predictAge(32, 54, 76, 65, 34, 63, 64, 45))
    }
}
