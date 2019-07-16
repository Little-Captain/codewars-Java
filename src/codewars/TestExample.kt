package codewars

import org.junit.Assert.assertArrayEquals
import kotlin.test.assertEquals
import org.junit.Test
import java.math.BigInteger
import java.util.*
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

fun generateColor(r: Random) = "#%06x".format(r.nextInt(0x1000000))

/**
 * 有时候，先用循环实现，再改写为简洁的序列形式，更容易
 * 因为往往，一开始就用序列实现，可能不那么容易实现
 */
fun cycle(n: Int) =
    if (n % 2 == 0 || n % 5 == 0) -1
    else generateSequence(10 % n) { it * 10 % n }
        .withIndex()
        .first { it.value == 1 }
        .index + 1

fun decomposeSingleStrand1(singleStand: String): String {
    if (singleStand.isEmpty() || singleStand.length == 1) {
        return "Frame 1: $singleStand\nFrame 2: $singleStand\nFrame 3: $singleStand"
    }
    val frame1 = singleStand.replace(Regex("(.{3})"), "$1 ").trimEnd()
    val frame2 = "${singleStand.substring(0, 1)} ${singleStand.substring(1, singleStand.length).replace(
        Regex("(.{3})"),
        "$1 "
    ).trimEnd()}"
    if (singleStand.length == 2) {
        return "Frame 1: $singleStand\nFrame 2: $frame2\nFrame 3: $singleStand"
    }
    val frame3 = "${singleStand.substring(0, 2)} ${singleStand.substring(2, singleStand.length).replace(
        Regex("(.{3})"),
        "$1 "
    ).trimEnd()}"
    return "Frame 1: $frame1\nFrame 2: $frame2\nFrame 3: $frame3"
}

fun decomposeDoubleStrand2(doubleStrand: String): String {
    return "hello world!"
}

// Tricky Kotlin #0: extension constructor
fun Int(s: String): Int = s.let { s.toInt() }

fun Long(s: String): Long = s.let { s.toLong() }
fun Double(s: String): Double = s.let { s.toDouble() }

fun comp(a: IntArray?, b: IntArray?): Boolean {
    if (a == null || b == null) return false
    val a = a.distinct()
    val b = b.distinct()
    if (a.size != b.size || a.isEmpty()) return false
    val tmp = a.map { it * it }
    for (i in tmp) {
        if (!b.contains(i)) {
            return false
        }
    }
    return true
}

class TestExample {

    @Test
    fun test_common() {
        val a1 = intArrayOf(121, 144, 19, 161, 19, 144, 19, 11)
        val a2 = intArrayOf(11 * 11, 121 * 121, 144 * 144, 19 * 19, 161 * 161, 19 * 19, 144 * 144, 19 * 19)

        assertEquals(true, comp(a1, a2))
    }

    @Test
    fun basic1() {
        assertEquals(
            decomposeSingleStrand1("AGGTGACACCGCAAGCCTTATATTAGC"),
            "Frame 1: AGG TGA CAC CGC AAG CCT TAT ATT AGC\nFrame 2: A GGT GAC ACC GCA AGC CTT ATA TTA GC\nFrame 3: AG GTG ACA CCG CAA GCC TTA TAT TAG C"
        )
        assertEquals(decomposeSingleStrand1(""), "Frame 1: \nFrame 2: \nFrame 3: ")
        assertEquals(decomposeSingleStrand1("A"), "Frame 1: A\nFrame 2: A\nFrame 3: A")
        assertEquals(decomposeSingleStrand1("AG"), "Frame 1: AG\nFrame 2: A G\nFrame 3: AG")
        assertEquals(decomposeSingleStrand1("AGG"), "Frame 1: AGG\nFrame 2: A GG\nFrame 3: AG G")
    }

    @Test
    fun basic2() {
        assertEquals(
            "Frame 1: AGG TGA CAC CGC AAG CCT TAT ATT AGC\nFrame 2: A GGT GAC ACC GCA AGC CTT ATA TTA GC\nFrame 3: AG GTG ACA CCG CAA GCC TTA TAT TAG C\n\nReverse Frame 1: GCT AAT ATA AGG CTT GCG GTG TCA CCT\nReverse Frame 2: G CTA ATA TAA GGC TTG CGG TGT CAC CT\nReverse Frame 3: GC TAA TAT AAG GCT TGC GGT GTC ACC T",
            decomposeDoubleStrand2("AGGTGACACCGCAAGCCTTATATTAGC")
        )
    }

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

    @Test
    fun basicTest1() {
        for (i in 0..9) {
            val x = generateColor(Random())
            println(x)
            assertEquals("#", x.substring(0, 1))
        }
    }

    private fun dotest(n: Int, expected: Int) {
        val actual = cycle(n)
        assertEquals(expected.toLong(), actual.toLong())
    }

    @Test
    fun fixedTests() {
        dotest(3, 1)
        dotest(33, 2)
        dotest(18118, -1)
        dotest(5, -1)
        dotest(13, 6)
        dotest(21, 6)
        dotest(27, 3)
        dotest(33, 2)
        dotest(37, 3)
        dotest(94, -1)
        dotest(317707, 24438)
    }

    @Test
    fun testInt() {
        val r = Random(System.currentTimeMillis())
        (0..100).forEach {
            r.nextInt().let {
                // assertEquals(it, kotlin.Int(it.toString()))
            }
        }
    }
}
