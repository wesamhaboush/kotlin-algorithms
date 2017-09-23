package algorithms

import org.junit.Test
import kotlin.test.assertEquals

class `largest pandigital prime` {
    @Test
    fun largest_pandigital_prime_test() {
        time {
            assertEquals(7652413, largestPrimePandigital())
        }
    }

}
