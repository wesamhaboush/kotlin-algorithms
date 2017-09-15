package algorithms

import org.junit.Test
import kotlin.test.assertEquals

class sieve {

    @Test
    fun sieveOfEratosthenes_test() {
        val primeIterator = sieveOfEratosthenes()
        assertEquals(2, primeIterator.next())
        assertEquals(3, primeIterator.next())
        assertEquals(5, primeIterator.next())
        assertEquals(7, primeIterator.next())
        assertEquals(11, primeIterator.next())
        assertEquals(13, primeIterator.next())
        assertEquals(17, primeIterator.next())
        assertEquals(19, primeIterator.next())
        assertEquals(23, primeIterator.next())
        assertEquals(29, primeIterator.next())
        assertEquals(31, primeIterator.next())
    }
}
