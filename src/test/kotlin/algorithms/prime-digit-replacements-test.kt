package algorithms

import org.junit.Test
import kotlin.test.assertEquals

class `prime-digit-replacements-test` {

    @Test
    fun primesWithDuplicateDigits_test() {
        time {
            val v = primesWithDuplicateDigits().iterator()
            assertEquals(11, v.next().first)
            assertEquals(101, v.next().first)
            assertEquals(113, v.next().first)
        }
    }

    @Test
    fun occurrenceList_test() {
        time {
            assertEquals(
                    mutableMapOf(
                            0 to mutableListOf(1, 2),
                            1 to mutableListOf(),
                            2 to mutableListOf(),
                            3 to mutableListOf(),
                            4 to mutableListOf(),
                            5 to mutableListOf(0),
                            6 to mutableListOf(),
                            7 to mutableListOf(),
                            8 to mutableListOf(),
                            9 to mutableListOf()
                    ),
                    occurrenceMap(numberToDigits(500))
            )
        }
    }

    @Test
    fun solution_test() {
        time {
            assertEquals(
                    Triple(
                            listOf(121313, 222323, 323333, 424343, 525353, 626363, 828383, 929393L),
                            listOf(1, 2, 1, 3, 1, 3),
                            Pair(1, listOf(0, 2, 4))
                    ),
                    findFamilyOfPrimesOfSize(8).first()
            )
        }
    }
}
