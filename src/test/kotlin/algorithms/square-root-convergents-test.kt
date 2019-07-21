package algorithms

import org.junit.Test
import kotlin.test.assertEquals

class `square-root-convergents-test` {

    @Test
    fun applyAsInt_should_detect_all_cases() {
        // given
        // when
        // then
        time { assertEquals(countMoreDigitsInNumerator(0), 0) }
        time { assertEquals(countMoreDigitsInNumerator(1), 0) }
        time { assertEquals(countMoreDigitsInNumerator(2), 0) }
        time { assertEquals(countMoreDigitsInNumerator(3), 0) }
        time { assertEquals(countMoreDigitsInNumerator(4), 0) }
        time { assertEquals(countMoreDigitsInNumerator(5), 0) }
        time { assertEquals(countMoreDigitsInNumerator(6), 0) }
        time { assertEquals(countMoreDigitsInNumerator(7), 0) }
        time { assertEquals(countMoreDigitsInNumerator(8), 1) }
        time { assertEquals(countMoreDigitsInNumerator(9), 1) }
        time { assertEquals(countMoreDigitsInNumerator(10), 1) }
        time { assertEquals(countMoreDigitsInNumerator(11), 1) }
        time { assertEquals(countMoreDigitsInNumerator(12), 1) }
        time { assertEquals(countMoreDigitsInNumerator(13), 2) }
        time { assertEquals(countMoreDigitsInNumerator(50), 7) }
        time { assertEquals(countMoreDigitsInNumerator(100), 15) }
    }

    @Test
    fun applyAsInt_problem57() {
        // given
        // when
        // then
        assertEquals(countMoreDigitsInNumerator(1001), 153)
    }
}
