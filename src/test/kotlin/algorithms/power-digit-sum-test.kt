package algorithms

import org.junit.Test
import kotlin.test.assertEquals

class `power-digit-sum-test` {

    @Test
    fun powerDigitSum_test() {
        time {
            assertEquals(1366, PowerDigitSum.sumDigitsOfExponent(2, 1000))
            assertEquals(2151, PowerDigitSum.sumDigitsOfExponent(3, 1020))
        }
    }

    @Test
    fun powerDigitSum2_crude_test() {
        time {
            assertEquals(972, PowerDigitSum.maxPowerDigitSumBruteforce(99, 99))
        }
    }

    @Test
    fun powerDigitSum_searcher_test() {
        time {
            assertEquals(972, PowerDigitSum.maxPowerDigitSumOptimized(99, 99))
            assertEquals(1450, PowerDigitSum.maxPowerDigitSumOptimized(99, 150))
            assertEquals(1045, PowerDigitSum.maxPowerDigitSumOptimized(150, 99))
        }
    }

    @Test
    fun powerDigitSum2_length() {
        time {
            assertEquals(1, PowerDigitSum.digitCountOf(1, 3))
            assertEquals(1, PowerDigitSum.digitCountOf(2, 3))
            assertEquals(2, PowerDigitSum.digitCountOf(2, 4))
            assertEquals(3, PowerDigitSum.digitCountOf(5, 4))
            assertEquals(201, PowerDigitSum.digitCountOf(100, 100))
        }
    }
}
