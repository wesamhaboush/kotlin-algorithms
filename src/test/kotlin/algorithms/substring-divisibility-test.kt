package algorithms

import org.junit.Test
import kotlin.test.assertEquals

class `substring-divisibility-test` {

    @Test
    fun first_digit_test() {
        time {
            val findAllValidPermutations = findAllValidPermutations()
            assertEquals(6, findAllValidPermutations.size)
            assertEquals("16695334890",sumDigitLists(findAllValidPermutations).joinToString("") { it.toString() })
            assertEquals(listOf(1, 6, 6, 9, 5, 3, 3, 4, 8, 9, 0), sumDigitLists(findAllValidPermutations))
            assertEquals(
                    setOf(
                            listOf(1, 4, 0, 6, 3, 5, 7, 2, 8, 9),
                            listOf(1, 4, 3, 0, 9, 5, 2, 8, 6, 7),
                            listOf(1, 4, 6, 0, 3, 5, 7, 2, 8, 9),
                            listOf(4, 1, 0, 6, 3, 5, 7, 2, 8, 9),
                            listOf(4, 1, 3, 0, 9, 5, 2, 8, 6, 7),
                            listOf(4, 1, 6, 0, 3, 5, 7, 2, 8, 9)
                    ),
                    findAllValidPermutations
            )
        }
    }
}
