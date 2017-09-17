package algorithms

import org.junit.Test
import kotlin.system.measureTimeMillis
import kotlin.test.assertEquals

class `pandigital-multiplies-test` {

    @Test
    fun maximum_pan_digital_concatenated_product_test() {
        val time = measureTimeMillis {
            assertEquals(932718654, maxPandigitalMultiply())
        }
        println("it took: $time ms")
    }
}
