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
}
