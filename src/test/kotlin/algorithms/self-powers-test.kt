package algorithms

import org.junit.Test
import kotlin.test.assertEquals

class `self-powers-test` {

    @Test
    fun firstNDigitsOf_test() {
        // 1^1 + 2^2 + 3^3 + 4^4 = 1 + 4 + 27 + 256 = 288
        // first digit is 8, first two digits is 30
        time { assertEquals(8, firstNDigitsOf(1, 4)) }
        time { assertEquals(88, firstNDigitsOf(2, 4)) }

        // 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317
        time { assertEquals(17, firstNDigitsOf(2, 10)) }
        time { assertEquals(317, firstNDigitsOf(3, 10)) }
        time { assertEquals(1317, firstNDigitsOf(4, 10)) }
        time { assertEquals(71317, firstNDigitsOf(5, 10)) }
        // the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.
        time { assertEquals(9110846700, firstNDigitsOf(10, 1000)) }
    }

    @Test
    fun intPower_test() {
        time { assertEquals(4, intPow(2, 2)) }
        time { assertEquals(8, intPow(2, 3)) }
        time { assertEquals(1024, intPow(4, 5)) }
        time { assertEquals(78125, intPow( 5, 7)) }
    }

    @Test
    fun moduloPower_test() {
        // note how this returns the first digit of the power
        time { assertEquals(4, moduloPower( 2, 2, 10)) }
        time { assertEquals(5, moduloPower( 5, 7, 10)) }
        // note that 5^7 = 78125, so the moduloPower returned the value of the first
        // two digits as expected.
        time { assertEquals(25, moduloPower(5, 7, 100)) }
    }

    @Test
    fun moduloSum_test() {
        // note how this returns the first digit of the Sum
        time { assertEquals(4, moduloSum(2, 2, 10)) }
        // note that 7 + 5 = 12, so first digit is 2
        time { assertEquals(2, moduloSum(7, 5, 10)) }
        // note that 7 + 5 = 12, so first 2 digits is 12
        time { assertEquals(12, moduloSum(7, 5, 100)) }
    }

    @Test
    fun moduloMultiply_test() {
        // note how this returns the first digit of the multiplication
        time { assertEquals(4, moduloMultiply(2, 2, 10)) }
        // note that 7 * 5 = 35, so first digit is 5
        time { assertEquals(5, moduloMultiply(7, 5, 10)) }
        // note that 7 * 5 = 35, so first 2 digits is 12
        time { assertEquals(35, moduloMultiply(7, 5, 100)) }
    }
}
