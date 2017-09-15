package algorithms

import org.junit.Test
import kotlin.test.assertEquals

class circular {

    @Test
    fun circular_primes_test() {
        val forZeroDigit = circularPrimes(1)
        assertEquals(0, forZeroDigit.size)
        assertEquals(setOf(), forZeroDigit)

        val forOneDigit = circularPrimes(10)
        assertEquals(setOf(2L, 3L, 5L, 7L).size, forOneDigit.size)
        assertEquals(setOf(2L, 3L, 5L, 7L), forOneDigit)

        val for2Digits = circularPrimes(100)
        assertEquals(13, for2Digits.size)
        assertEquals(setOf(2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, 97L), for2Digits)

        val for3Digits = circularPrimes(1000)
        assertEquals(25, for3Digits.size)
        assertEquals(setOf(2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, 97,
                113, 131, 197, 199, 311, 337, 373, 719, 733, 919, 971, 991L), for3Digits)

        val for4Digits = circularPrimes(10000)
        assertEquals(33, for4Digits.size)
        assertEquals(setOf(2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, 97,
                113, 131, 197, 199, 311, 337, 373, 719, 733, 919, 971, 991,
                1193, 1931, 3119, 3779, 7793, 7937, 9311, 9377L), for4Digits)

        val for5Digits = circularPrimes(100000)
        assertEquals(43, for5Digits.size)
        assertEquals(
                setOf(
                        2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, 97,
                        113, 131, 197, 199, 311, 337, 373, 719, 733, 919, 971, 991,
                        1193, 1931, 3119, 3779, 7793, 7937, 9311, 9377,
                        11939, 19391, 19937, 37199, 39119, 71993, 91193, 93719, 93911, 99371L
                ),
                for5Digits
        )

        val for6Digits = circularPrimes(1000000)
        assertEquals(55, for6Digits.size)
        assertEquals(
                setOf(
                        2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, 97,
                        113, 131, 197, 199, 311, 337, 373, 719, 733, 919, 971, 991,
                        1193, 1931, 3119, 3779, 7793, 7937, 9311, 9377,
                        11939, 19391, 19937, 37199, 39119, 71993, 91193, 93719, 93911, 99371,
                        193939, 199933, 319993, 331999, 391939, 393919, 919393, 933199, 939193, 939391, 993319, 999331L
                ),
                for6Digits)
    }
}
