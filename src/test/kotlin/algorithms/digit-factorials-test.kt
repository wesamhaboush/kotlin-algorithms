package algorithms

import org.junit.Test
import kotlin.test.assertEquals

class `digit-factorials-test` {


    @Test
    fun sumFactorialOfDigitsSpecs() {
        assertEquals(1, sumFactorialOfDigits(number = 0))
        assertEquals(1, sumFactorialOfDigits(number = 1))
        assertEquals(2, sumFactorialOfDigits(number = 2))
        assertEquals(2, sumFactorialOfDigits(number = 10))
        assertEquals(3, sumFactorialOfDigits(number = 101))
        assertEquals(9, sumFactorialOfDigits(number = 132))
    }

    @Test
    fun findAllNumbersEqualToSumOfDigitFactorials_test() {
        val sum: Long = 145 + 40585
        assertEquals(sum, findAllNumbersEqualToSumOfDigitFactorials())
    }
}
