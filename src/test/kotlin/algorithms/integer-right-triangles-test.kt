package algorithms

import org.junit.Test
import kotlin.system.measureTimeMillis
import kotlin.test.assertEquals

class `integer-right-triangles-test` {

    @Test
    fun pairs_test1() {
        val time = measureTimeMillis {
            assertEquals(
                    mapOf(12L to setOf(setOf(3L, 4, 5))),
                    max_p_solution_count(12)
            )
        }
        println("it took: $time ms")
    }

    @Test
    fun pairs_test2() {
        val time = measureTimeMillis {
            assertEquals(
                    mapOf(12L to setOf(setOf(3L, 4, 5)),
                            30L to setOf(setOf(5L, 12, 13)),
                            24L to setOf(setOf(6L, 8, 10)),
                            40L to setOf(setOf(8L, 15, 17)),
                            36L to setOf(setOf(9L, 12, 15)),
                            48L to setOf(setOf(12L, 16, 20))),
                    max_p_solution_count(50)
            )
        }
        println("it took: $time ms")
    }

    @Test
    fun pairs_test3() {
        val time = measureTimeMillis {
            max_p_solution_count(1000)
                    .entries
                    .maxBy { it.value.size }
                    .let {
//                        println(it)
                        assertEquals(840L, it?.key)
                        assertEquals(8, it?.value?.size)
                        assertEquals(
                                setOf(
                                        setOf(40, 399, 401L),
                                        setOf(56, 390, 394L),
                                        setOf(105, 360, 375L),
                                        setOf(120, 350, 370L),
                                        setOf(140, 336, 364L),
                                        setOf(168, 315, 357L),
                                        setOf(210, 280, 350L),
                                        setOf(240, 252, 348L)
                                ),
                                it?.value)
                    }
        }
        println("it took: $time ms")
    }
}
