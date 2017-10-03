package algorithms

import org.junit.Test
import kotlin.test.assertEquals

class `triangular-hexagonal-pentagonal-test` {

    @Test
    fun triangulars_test() {
        time {
            /*
            Thp(tn=(1, 1), hn=(1, 1), pn=(1, 1))
            Thp(tn=(285, 40755), hn=(143, 40755), pn=(165, 40755))
            Thp(tn=(55385, 1533776805), hn=(27693, 1533776805), pn=(31977, 1533776805))
             */
            val triangularHexagonalPentagonals = triangularHexagonalPentagonals().iterator()
            assertEquals(Thp(Pair(1, 1), Pair(1, 1), Pair(1, 1)), triangularHexagonalPentagonals.next())
            assertEquals(Thp(Pair(285, 40755), Pair(143, 40755), Pair(165, 40755)), triangularHexagonalPentagonals.next())
            assertEquals(Thp(Pair(55385, 1533776805), Pair(27693, 1533776805), Pair(31977, 1533776805)), triangularHexagonalPentagonals.next())
        }
    }
}
