package algorithms;

import org.junit.Test
import kotlin.test.assertEquals

class `combinatoric-selections-test` {


    @Test
    fun primeAsSumOfLongestConsecutiveSequence_test() {
        time {
            assertEquals(0, countCombinationsAboveMaxForAllN(6, 50))
            assertEquals(0, countCombinationsAboveMaxForAllN(7, 50))
            assertEquals(0, countCombinationsAboveMaxForAllN(8, 50))
            assertEquals(3, countCombinationsAboveMaxForAllN(9, 50))
            assertEquals(7, countCombinationsAboveMaxForAllN(10, 50))
            assertEquals(12, countCombinationsAboveMaxForAllN(11, 50))
            assertEquals(20, countCombinationsAboveMaxForAllN(12, 50))
            assertEquals(4, countCombinationsAboveMaxForAllN(24, 1_000_000L))
        }

        time {
            assertEquals(4075, countCombinationsAboveMaxForAllN(101, 1_000_000L)) // for n = 100, we need 101 rows
        }
    }
}
