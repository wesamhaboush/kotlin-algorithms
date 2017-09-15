package algorithms

import org.junit.Test
import kotlin.test.assertEquals

class `truncatable-primes-test` {

    @Test
    fun sumOfRightAndLeftTruncatablePrimes_test() {
        assertEquals(748317, sumOfRightAndLeftTruncatablePrimes())
    }
}
