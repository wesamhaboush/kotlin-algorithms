package algorithms

import org.junit.Test
import kotlin.test.assertEquals

class `lychrel-numbers-test` {

    @Test
    fun howManyLychrelNumbersBelow_test() {
        time {
            assertEquals(0, lychrelNumbersBelow(10).size)
            assertEquals(0, lychrelNumbersBelow(11).size)
            assertEquals(1, lychrelNumbersBelow(200).size)
            assertEquals(2, lychrelNumbersBelow(300).size)
            assertEquals(249, lychrelNumbersBelow(10000).size)
        }
    }

    @Test
    fun isLychrelWithin50_test() {
        time {
            assertEquals(11, isLychrelWithin50(10))
            assertEquals(22, isLychrelWithin50(11))
            assertEquals(202, isLychrelWithin50(200))
            assertEquals(303, isLychrelWithin50(300))
            assertEquals(-1, isLychrelWithin50(196))
            assertEquals(7337, isLychrelWithin50(349))
            assertEquals(-1, isLychrelWithin50(4994))

            val lychrelNumbers = lychrelNumbersBelowNaive(10000)
            assertEquals(249, lychrelNumbers.size)
        }
    }

    @Test
    fun compareDigitLists_test() {
        time {
            assertEquals(0, compareDigitLists(listOf(1, 2, 4), listOf(1, 2, 4)))
            assertEquals(-1, compareDigitLists(listOf(1, 2, 4), listOf(1, 2, 5)))
            assertEquals(1, compareDigitLists(listOf(1, 2, 5), listOf(1, 2, 4)))
            assertEquals(1, compareDigitLists(listOf(1, 2, 4), listOf(1, 2)))
            assertEquals(-1, compareDigitLists(listOf(1, 2, 4), listOf(1, 2, 3, 4)))
            assertEquals(1, compareDigitLists(listOf(7, 1, 2, 4), listOf(1, 2, 3, 4)))
        }
    }

    @Test
    fun findNext_test() {
        time {
            assertEquals(listOf(1, 1), reverseAndAddDigits(numberToDigits(10)))
            assertEquals(listOf(3, 3), reverseAndAddDigits(numberToDigits(12)))
            assertEquals(listOf(1, 2, 9, 2), reverseAndAddDigits(numberToDigits(349)))
            assertEquals(listOf(4, 2, 1, 3), reverseAndAddDigits(listOf(1, 2, 9, 2)))
            assertEquals(listOf(7, 3, 3, 7), reverseAndAddDigits(listOf(4, 2, 1, 3)))
        }
    }

    @Test
    fun sumListsAsNumbers_test() {
        time {
            assertEquals(
                    listOf(3),
                    addAsNumbers(
                            listOf(
                                    listOf(1),
                                    listOf(2)
                            )
                    )
            )

            assertEquals(
                    listOf(2),
                    addAsNumbers(
                            listOf(
                                    listOf(),
                                    listOf(2)
                            )
                    )
            )

            assertEquals(
                    listOf(1, 4),
                    addAsNumbers(
                            listOf(
                                    listOf(1, 2),
                                    listOf(2)
                            )
                    )
            )
            assertEquals(
                    listOf(4, 6),
                    addAsNumbers(
                            listOf(
                                    listOf(1, 2),
                                    listOf(3, 4)
                            )
                    )
            )

            assertEquals(
                    listOf(1, 6, 1),
                    addAsNumbers(
                            listOf(
                                    listOf(1, 2, 7),
                                    listOf(3, 4)
                            )
                    )
            )
            assertEquals(
                    listOf(1, 6, 0, 2),
                    addAsNumbers(
                            listOf(
                                    listOf(1, 2, 7),
                                    listOf(1, 3, 4, 5),
                                    listOf(1, 2, 7),
                                    listOf(3)
                            )
                    )
            )
            assertEquals(
                    listOf(1, 2, 7),
                    addAsNumbers(
                            listOf(
                                    listOf(1, 2, 7)
                            )
                    )
            )
            assertEquals(
                    listOf(1, 2, 7),
                    addAsNumbers(
                            listOf(
                                    listOf(1, 2, 7),
                                    listOf()
                            )
                    )
            )
            assertEquals(
                    listOf(),
                    addAsNumbers(
                            listOf(
                                    listOf()
                            )
                    )
            )
            assertEquals(
                    listOf(),
                    addAsNumbers(
                            listOf(
                            )
                    )
            )
        }
    }


}
