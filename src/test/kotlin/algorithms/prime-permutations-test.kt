package algorithms

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class `prime-permutations` {

    @Test
    fun primesOf4Digits_test2() {
        time {
            val primesOf4DigitsIt = primesOf4Digits().asIterable().iterator()
            assertEquals(1009, primesOf4DigitsIt.next())
            assertEquals(1013, primesOf4DigitsIt.next())
            assertEquals(1019, primesOf4DigitsIt.next())
        }
    }

    @Test
    fun withSameDistance_test() {
        time {
            assertTrue { withSameDistance(listOf(1, 5, 9)) }
            assertFalse { withSameDistance(listOf(5, 6, 8)) }
        }
    }

    @Test
    fun listsWithCharacteristics_test() {
        time {
            val list = listsWithCharacteristics()
            assertEquals(
                    listOf(
                            listOf(1487L, 4817, 8147),
                            listOf(2969L, 6299, 9629)
                    ),
                    list
            )
        }
    }
}
