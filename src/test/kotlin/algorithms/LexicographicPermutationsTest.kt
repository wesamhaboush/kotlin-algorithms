package algorithms

import org.junit.Test
import kotlin.test.assertEquals

class LexicographicPermutationsTest {
    @Test
    operator fun next() {
        time {
            val lexicographicPermutations = LexicographicPermutations(listOf(1, 2, 4))
            assertEquals(listOf(1, 2, 4), lexicographicPermutations.next())
            assertEquals(listOf(1, 4, 2), lexicographicPermutations.next())
            assertEquals(listOf(2, 1, 4), lexicographicPermutations.next())
            assertEquals(listOf(2, 4, 1), lexicographicPermutations.next())
            assertEquals(listOf(4, 1, 2), lexicographicPermutations.next())
            assertEquals(listOf(4, 2, 1), lexicographicPermutations.next())
        }
    }

}
