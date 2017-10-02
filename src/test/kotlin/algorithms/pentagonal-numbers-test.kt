package algorithms

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class `pentagonal-numbers-test` {

    @Test
    fun pentagonalNumbers_test() {
        time {
            val pentagonalNumbersSequence = pentagonalNumbers().iterator()
            assertEquals(Pair(1, 1), pentagonalNumbersSequence.next() )
            assertEquals(Pair(2, 5), pentagonalNumbersSequence.next() )
            assertEquals(Pair(3,12), pentagonalNumbersSequence.next() )
            assertEquals(Pair(4, 22), pentagonalNumbersSequence.next() )
            assertEquals(Pair(5, 35), pentagonalNumbersSequence.next() )
            assertEquals(Pair(6, 51), pentagonalNumbersSequence.next() )
            assertEquals(Pair(7, 70), pentagonalNumbersSequence.next() )
            assertEquals(Pair(8, 92), pentagonalNumbersSequence.next() )
            assertEquals(Pair(9, 117), pentagonalNumbersSequence.next() )
            assertEquals(Pair(10, 145), pentagonalNumbersSequence.next() )
        }
    }

    @Test
    fun pentagonalNumbersDownFrom_test() {
        time {
            val pentagonalNumbersSequence = pentagonalNumbersDownFrom(10).iterator()
            assertEquals(Pair(10, 145), pentagonalNumbersSequence.next() )
            assertEquals(Pair(9, 117), pentagonalNumbersSequence.next() )
            assertEquals(Pair(8, 92), pentagonalNumbersSequence.next() )
            assertEquals(Pair(7, 70), pentagonalNumbersSequence.next() )
            assertEquals(Pair(6, 51), pentagonalNumbersSequence.next() )
            assertEquals(Pair(5, 35), pentagonalNumbersSequence.next() )
            assertEquals(Pair(4, 22), pentagonalNumbersSequence.next() )
            assertEquals(Pair(3, 12), pentagonalNumbersSequence.next() )
            assertEquals(Pair(2, 5), pentagonalNumbersSequence.next() )
            assertEquals(Pair(1, 1), pentagonalNumbersSequence.next() )
        }
    }

    @Test
    fun isPentagonalNumber_test() {
        time {
            assertTrue(isPentagonalNumber(1))
            assertTrue(isPentagonalNumber(5))
            assertTrue(isPentagonalNumber(12))
            assertTrue(isPentagonalNumber(22))
            assertTrue(isPentagonalNumber(35))
            assertTrue(isPentagonalNumber(51))
            assertTrue(isPentagonalNumber(70))
            assertTrue(isPentagonalNumber(92))
            assertTrue(isPentagonalNumber(117))
            assertTrue(isPentagonalNumber(145))
            assertFalse(isPentagonalNumber(146))
            assertFalse(isPentagonalNumber(0))
            assertFalse(isPentagonalNumber(2))
        }
    }

    @Test
    fun findTargetPentagonalPair_test() {
        time {
            val result = findTargetPentagonalPair()
            assertEquals(Pair(Pair(2167, 7042750), Pair(1020, 1560090)), result)
            assertEquals(5482660, result.first.second - result.second.second)
            println(result)
        }
    }
}
