package algorithms

import org.junit.Test
import kotlin.test.assertEquals

class Goldbachs_other_conjecture_test {
    @Test
    fun firstOddCompositeBreakingGoldbachsOtherConjecture_test() {
        time { assertEquals(5777, firstOddCompositeBreakingGoldbachsOtherConjecture()) }
    }

}
