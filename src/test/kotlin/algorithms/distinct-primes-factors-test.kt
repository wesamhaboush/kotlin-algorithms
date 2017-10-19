package algorithms

import org.junit.Test
import kotlin.test.assertEquals

class `distinct-prime-factors-test` {


    @Test
    fun prime_factors() {
        time { assertEquals(listOf(2L, 7L), primeFactors(14)) }
        time { assertEquals(listOf(3L, 5L), primeFactors(15)) }
        time { assertEquals(listOf(2L, 2L, 7L, 23L), primeFactors(644)) }
    }

    @Test
    fun prime_factors_set() {
        time { assertEquals(setOf(2L, 7L), uniqPrimeFactors(14)) }
        time { assertEquals(setOf(3L, 5L), uniqPrimeFactors(15)) }
        time { assertEquals(setOf(2L, 7L, 23L), uniqPrimeFactors(644)) }
    }

    @Test
    fun firstFourConsecutiveIntegersWithFourDistinctPrimeFactors_set() {
        time {
            assertEquals(listOf(134043, 134044, 134045, 134046L),
                    firstNConsecutiveIntegersWithNDistinctPrimeFactors(4))
        }
    }
}

