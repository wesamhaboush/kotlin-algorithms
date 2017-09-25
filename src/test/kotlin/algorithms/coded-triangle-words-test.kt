package algorithms

import org.junit.Test
import kotlin.test.assertEquals

class `coded-triangle-words-test` {


    @Test
    fun unquote_test() {
        time { assertEquals("abc", unquote("\"abc\"")) }
    }

    @Test
    fun letterToNumber_test() {
        time { assertEquals(1, letterToNumber('A')) }
        time { assertEquals(2, letterToNumber('B')) }

        time { assertEquals(1, letterToNumber('a')) }
    }

    @Test
    fun wordToNumber_test() {
        time { assertEquals(55, wordToNumber("SKY")) }
    }

    @Test
    fun countNumberOfTriangularWords_test() {
        time { assertEquals(162, countNumberOfTriangularWords()) }
    }
}
