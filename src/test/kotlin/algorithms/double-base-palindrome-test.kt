package algorithms

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class `double-base-palindrome-test` {

    @Test
    fun findPalindromicNumbersInBothBinaryAndDecimalBelow_test() {
        //[0, 1, 3, 5, 7, 9,
        // 33, 99,
        // 313, 585, 717,
        // 7447, 9009,
        // 15351, 32223, 39993, 53235, 53835, 73737,
        // 585585
        // ]
        val palindromicBinaryAndDecimal = findPalindromicNumbersInBothBinaryAndDecimalBelow(1000000).toList()
        assertEquals(listOf(
                0L, 1, 3, 5, 7, 9, 33, 99, 313, 585, 717,
                7447, 9009, 15351, 32223, 39993, 53235, 53835, 73737, 585585
        ), palindromicBinaryAndDecimal)
        assertEquals(872187, palindromicBinaryAndDecimal.sum())
    }

    @Test
    fun isDecimalPalindromic_test() {
        assertTrue(isDecimalPalindromic(11))
        assertTrue(isDecimalPalindromic(585))
    }

    @Test
    fun isBinaryPalindromic_test() {
        assertTrue(isBinaryPalindromic(3))
        assertTrue(isBinaryPalindromic(7))
        assertTrue(isBinaryPalindromic(585))
    }
}
