package algorithms

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class `pentagonal-numbers-test` {

    @Test
    fun pentagonalNumbers_test() {
        time {
            val pentagonalNumbersSequence = pentagonals().iterator()
            assertEquals(Pair(1L, 1L), pentagonalNumbersSequence.next() )
            assertEquals(Pair(2L, 5L), pentagonalNumbersSequence.next() )
            assertEquals(Pair(3L,12L), pentagonalNumbersSequence.next() )
            assertEquals(Pair(4L, 22L), pentagonalNumbersSequence.next() )
            assertEquals(Pair(5L, 35L), pentagonalNumbersSequence.next() )
            assertEquals(Pair(6L, 51L), pentagonalNumbersSequence.next() )
            assertEquals(Pair(7L, 70L), pentagonalNumbersSequence.next() )
            assertEquals(Pair(8L, 92L), pentagonalNumbersSequence.next() )
            assertEquals(Pair(9L, 117L), pentagonalNumbersSequence.next() )
            assertEquals(Pair(10L, 145L), pentagonalNumbersSequence.next() )
        }
    }

    @Test
    fun pentagonalNumbersDownFrom_test() {
        time {
            val pentagonalNumbersSequence = pentagonalsDownwards(10).iterator()
            assertEquals(Pair(10L, 145L), pentagonalNumbersSequence.next() )
            assertEquals(Pair(9L, 117L), pentagonalNumbersSequence.next() )
            assertEquals(Pair(8L, 92L), pentagonalNumbersSequence.next() )
            assertEquals(Pair(7L, 70L), pentagonalNumbersSequence.next() )
            assertEquals(Pair(6L, 51L), pentagonalNumbersSequence.next() )
            assertEquals(Pair(5L, 35L), pentagonalNumbersSequence.next() )
            assertEquals(Pair(4L, 22L), pentagonalNumbersSequence.next() )
            assertEquals(Pair(3L, 12L), pentagonalNumbersSequence.next() )
            assertEquals(Pair(2L, 5L), pentagonalNumbersSequence.next() )
            assertEquals(Pair(1L, 1L), pentagonalNumbersSequence.next() )
        }
    }

    @Test
    fun isPentagonalNumber_test() {
        time {
            assertTrue(isPentagonal(1))
            assertTrue(isPentagonal(5))
            assertTrue(isPentagonal(12))
            assertTrue(isPentagonal(22))
            assertTrue(isPentagonal(35))
            assertTrue(isPentagonal(51))
            assertTrue(isPentagonal(70))
            assertTrue(isPentagonal(92))
            assertTrue(isPentagonal(117))
            assertTrue(isPentagonal(145))
            assertFalse(isPentagonal(146))
            assertFalse(isPentagonal(0))
            assertFalse(isPentagonal(2))
        }
    }

    @Test
    fun findTargetPentagonalPair_test() {
        time {
            val result = findTargetPentagonalPair()
            assertEquals(Pair(Pair(2167L, 7042750L), Pair(1020L, 1560090L)), result)
            assertEquals(5482660, result.first.second - result.second.second)
        }
    }
}
