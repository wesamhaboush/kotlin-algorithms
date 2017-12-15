package algorithms

import org.junit.Test
import kotlin.test.assertEquals

class `permuted-multiples-test` {

    @Test
    fun findNumberWithSameDigitMultiplies_test() {
        time {
            val sequence = findNumber()
            assertEquals(
                    Triple(
                            142857L,
                            listOf(285714L, 428571, 571428, 714285, 857142),
                            listOf(1, 4, 2, 8, 5, 7)
                    ),
                    sequence.first()
            )
        }
    }
}
