package algorithms

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class `Math-Test` {
    @Test
    fun isPrime() {
        assertTrue(isPrime(2))
        assertTrue(isPrime(3))
        assertTrue(isPrime(11))
        assertTrue(isPrime(31))
        assertFalse(isPrime(32))
        assertFalse(isPrime(1))
    }

    @Test
    fun divides3_test() {
        assertTrue(divides3(3))
        assertTrue(divides3(33))
        assertTrue(divides3(39))
        assertTrue(divides3(61239))
        assertTrue(divides3(0))
        assertFalse(divides3(1))
        assertFalse(divides3(11))
        assertFalse(divides3(112))
        assertFalse(divides3(11278))
    }

    @Test
    fun factorial_test() {
        assertEquals(factorial(number = 0), 1)
        assertEquals(factorial(number = 1), 1)
        assertEquals(factorial(number = 2), 2)
        assertEquals(factorial(number = 3), 6)
        assertEquals(factorial(number = 4), 24)
        assertEquals(factorial(number = 5), 120)
    }

    @Test
    fun countfDigitsInNumber_test() {
        assertEquals(1, countDigitsInNumber(0))
        assertEquals(1, countDigitsInNumber(1))
        assertEquals(2, countDigitsInNumber(22))
        assertEquals(3, countDigitsInNumber(221))
        assertEquals(4, countDigitsInNumber(2210))
    }

    @Test
    fun numberToDigits_test() {
        assertEquals(listOf(0), numberToDigits(0))
        assertEquals(listOf(1), numberToDigits(1))
        assertEquals(listOf(1, 1, 1), numberToDigits(111))
        assertEquals(listOf(2, 1, 1), numberToDigits(211))
        assertEquals(listOf(2, 3, 1), numberToDigits(231))
    }

    @Test
    fun digitsToNumber_test() {
        assertEquals(0, digitsToNumber(listOf(0)))
        assertEquals(1, digitsToNumber(listOf(1)))
        assertEquals(111, digitsToNumber(listOf(1, 1, 1)))
        assertEquals(211, digitsToNumber(listOf(2, 1, 1)))
        assertEquals(231, digitsToNumber(listOf(2, 3, 1)))
    }

    @Test
    fun allCirculationsOfList_test() {
        assertEquals(listOf(listOf(0)), allCirculationsOfList(listOf(0)))
        assertEquals(listOf(
                listOf(0, 1),
                listOf(1, 0)
        ), allCirculationsOfList(listOf(0, 1)))
        assertEquals(listOf(
                listOf(0, 1, 2),
                listOf(1, 2, 0),
                listOf(2, 0, 1)
        ), allCirculationsOfList(listOf(0, 1, 2)))
        assertEquals(listOf(
                listOf(0, 1, 2, 3),
                listOf(1, 2, 3, 0),
                listOf(2, 3, 0, 1),
                listOf(3, 0, 1, 2)
        ), allCirculationsOfList(listOf(0, 1, 2, 3)))
    }

    @Test
    fun isPowerOf_test() {
        assertTrue(isPowerOf(8, 2))
        assertTrue(isPowerOf(1, 2))
        assertTrue(isPowerOf(2, 2))
        assertFalse(isPowerOf(3, 2))
        assertFalse(isPowerOf(7, 15))
    }

    @Test
    fun isPalindromic_test() {
        assertTrue(isPalindromic(listOf(0)))
        assertTrue(isPalindromic(listOf(0, 0)))
        assertTrue(isPalindromic(listOf(0, 0, 0)))
        assertTrue(isPalindromic(listOf(0, 1, 0)))
        assertFalse(isPalindromic(listOf(0, 1)))
        assertFalse(isPalindromic(listOf(0, 1, 2)))
    }

    @Test
    fun toBase_test() {
        assertEquals(0, toBase(0, 2L))
        assertEquals(1, toBase(1, 2L))
        assertEquals(11, toBase(3, 2L))
        assertEquals(111110100, toBase(500, 2L))
    }

    @Test
    fun primes_test() {
        val primes = primes(10)
        assertEquals(2, primes.next())
        assertEquals(3, primes.next())
        assertEquals(5, primes.next())
        assertEquals(7, primes.next())
        assertEquals(11, primes.next())
        assertEquals(13, primes.next())
        assertEquals(17, primes.next())
        assertEquals(19, primes.next())
        assertEquals(23, primes.next())
        assertEquals(29, primes.next())
        assertEquals(31, primes.next())
        assertEquals(37, primes.next())
        assertEquals(41, primes.next())
        assertEquals(43, primes.next())
        assertEquals(47, primes.next())
        assertEquals(53, primes.next())
        assertEquals(59, primes.next())
        assertEquals(61, primes.next())
        assertEquals(67, primes.next())
        assertEquals(71, primes.next())
        assertEquals(73, primes.next())
        assertEquals(79, primes.next())
        assertEquals(83, primes.next())
        assertEquals(89, primes.next())
        assertEquals(97, primes.next())
        assertEquals(101, primes.next())
    }

    @Test
    fun findFactors_test() {
        assertEquals(listOf<Pair<Long, Long>>(Pair(1, 2L)), findFactors(2L))
        assertEquals(listOf<Pair<Long, Long>>(Pair(1, 3L)), findFactors(3L))
        assertEquals(
                listOf<Pair<Long, Long>>(Pair(1, 4L), Pair(2, 2)),
                findFactors(4L)
        )
        assertEquals(
                listOf<Pair<Long, Long>>(Pair(1, 5L)),
                findFactors(5L)
        )
        assertEquals(
                listOf<Pair<Long, Long>>(Pair(1, 6L), Pair(2, 3), Pair(3, 2)),
                findFactors(6L)
        )
    }

    @Test
    fun isPerfectSquare_test() {
        time { assertTrue(isPerfectSquare(0L)) }
        time { assertTrue(isPerfectSquare(1L)) }
        time { assertTrue(isPerfectSquare(4L)) }
        time { assertTrue(isPerfectSquare(81L)) }
        time { assertFalse(isPerfectSquare(-1L)) }
        time { assertFalse(isPerfectSquare(2L)) }
        time { assertFalse(isPerfectSquare(22L)) }
        time { assertFalse(isPerfectSquare(101L)) }
    }

    @Test
    fun isTriangularNumber_test() {
        //1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
        time { assertTrue(isTriangularNumber(55L)) }
        time { assertTrue(isTriangularNumber(45)) }
        time { assertTrue(isTriangularNumber(36)) }
        time { assertTrue(isTriangularNumber(28)) }
        time { assertTrue(isTriangularNumber(21)) }
        time { assertTrue(isTriangularNumber(15)) }
        time { assertTrue(isTriangularNumber(10)) }
        time { assertTrue(isTriangularNumber(6)) }
        time { assertTrue(isTriangularNumber(3)) }
        time { assertTrue(isTriangularNumber(1)) }
        time { assertFalse(isTriangularNumber(56L)) }
        time { assertFalse(isTriangularNumber(16L)) }
    }

    @Test
    fun powerSet_test() {
        time {
            assertEquals(
                    setOf(setOf(), setOf(1), setOf(2), setOf(1, 2), setOf(4), setOf(1, 4), setOf(2, 4), setOf(1, 2, 4)),
                    powerSet(setOf(1, 2, 4))
            )
        }
        time {
            assertEquals(
                    setOf(setOf(), setOf(1), setOf(4), setOf(1, 4)),
                    powerSet(setOf(1, 1, 4))
            )
        }
    }

    @Test
    fun combinations_test() {
        time {
            assertEquals(
                    setOf(
                            listOf(), listOf(1), listOf(2), listOf(1, 2), listOf(4), listOf(1, 4),
                            listOf(2, 4), listOf(1, 2, 4)
                    ),
                    Combinations.of(listOf(1, 2, 4))
            )
        }
        time {
            assertEquals(
                    setOf(
                            listOf(), listOf(1), listOf(4), listOf(1, 1), listOf(1, 4), listOf(1, 1, 4)
                    ),
                    Combinations.of(listOf(1, 1, 4))
            )
        }
        time {
            assertEquals(
                    setOf(
                            listOf(1, 1), listOf(1, 4)
                    ),
                    Combinations.of(2, listOf(1, 1, 4))
            )
        }
    }

    @Test
    fun findSum_test() {
        time {
            assertEquals(listOf(3), findSum(listOf(1), listOf(2)))
            assertEquals(listOf(3, 3), findSum(listOf(1), listOf(3, 2)))
            assertEquals(listOf(4, 0, 3), findSum(listOf(3, 7, 1), listOf(3, 2)))
            assertEquals(listOf(6, 0, 3), findSum(listOf(3, 7, 1), listOf(2, 3, 2)))
            assertEquals(listOf(1, 1, 0, 3), findSum(listOf(5, 7, 1), listOf(5, 3, 2)))
        }
    }

    @Test
    fun triangulars_test() {
        time {
            val triangularsIterator = triangulars().iterator()
            //Tn=n(n+1)/2	 	1, 3, 6, 10, 15, ...
            assertEquals(Pair(1L, 1L), triangularsIterator.next())
            assertEquals(Pair(2L, 3L), triangularsIterator.next())
            assertEquals(Pair(3L, 6L), triangularsIterator.next())
            assertEquals(Pair(4L, 10L), triangularsIterator.next())
            assertEquals(Pair(5L, 15L), triangularsIterator.next())
        }
    }

    @Test
    fun hexagonals_test() {
        time {
            val hexagonalsIterator = hexagonals().iterator()
            //Hn=n(2n−1)	 	1, 6, 15, 28, 45, ...
            assertEquals(Pair(1L, 1L), hexagonalsIterator.next())
            assertEquals(Pair(2L, 6L), hexagonalsIterator.next())
            assertEquals(Pair(3L, 15L), hexagonalsIterator.next())
            assertEquals(Pair(4L, 28L), hexagonalsIterator.next())
            assertEquals(Pair(5L, 45L), hexagonalsIterator.next())
        }
    }

    @Test
    fun sumDigitLists_test() {
        time {
            assertEquals(listOf(3), sumDigitLists(
                    listOf(
                            listOf(1),
                            listOf(2)
                    )
            ))
            assertEquals(listOf(3, 3), sumDigitLists(
                    listOf(
                            listOf(1),
                            listOf(3, 2)
                    )
            ))
            assertEquals(listOf(4, 0, 3), sumDigitLists(
                    listOf(
                            listOf(3, 7, 1),
                            listOf(3, 2)
                    )
            ))
            assertEquals(listOf(6, 0, 3), sumDigitLists(
                    listOf(
                            listOf(3, 7, 1),
                            listOf(2, 3, 2)
                    )
            ))
            assertEquals(listOf(6, 4, 2, 3), sumDigitLists(
                    listOf(
                            listOf(5, 7, 1),
                            listOf(5, 3, 2),
                            listOf(5, 3, 2, 0)
                    )
            ))
        }
    }

    @Test
    fun gcd_test() {
        time { assertEquals(1, gcd(3, 5)) }
        time { assertEquals(3, gcd(3, 6)) }
        time { assertEquals(2, gcd(2, 8)) }

        time { assertEquals(1, gcd(listOf(3, 5))) }
        time { assertEquals(3, gcd(setOf(3, 6))) }
        time { assertEquals(2, gcd(listOf(2, 8))) }
        time { assertEquals(2, gcd(listOf(2, 8, 4))) }
        time { assertEquals(7, gcd(listOf(28, 21, 56))) }
    }

    @Test
    fun euclidFactors_test() {
        time { assertEquals(listOf(3, 5L), euclidFactors(listOf(3, 5))) }
        time { assertEquals(listOf(1, 2L), euclidFactors(setOf(3, 6))) }
        time { assertEquals(listOf(1, 4L), euclidFactors(listOf(2, 8))) }
        time { assertEquals(listOf(1, 4, 2L), euclidFactors(listOf(2, 8, 4))) }
        time { assertEquals(listOf(4, 3, 8L), euclidFactors(listOf(28, 21, 56))) }
    }

    @Test
    fun nextPrime_test() {
        time { assertEquals(2, nextPrime(0)) }
        time { assertEquals(2, nextPrime(2)) }
        time { assertEquals(3, nextPrime(3)) }
        time { assertEquals(5, nextPrime(4)) }
        time { assertEquals(5, nextPrime(5)) }
        time { assertEquals(7, nextPrime(6)) }
        time { assertEquals(7, nextPrime(7)) }
        time { assertEquals(11, nextPrime(8)) }
    }
}
