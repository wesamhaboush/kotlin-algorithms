package algorithms

private class NaturalNumbersGreaterThan(minimum: Long): Iterator<Long> {
    var value = minimum + 1
    override fun hasNext(): Boolean = true
    override fun next(): Long = value++
}

private class ExcludeMultiples(val n: Long, val base: Iterator<Long>): Iterator<Long> {
    override fun hasNext(): Boolean = true

    override fun next(): Long {
        while(true) {
            val i = base.next()
            if(i % n != 0L) return i
        }
    }
}


fun sieveOfEratosthenes(): Iterator<Long> {
    return object: Iterator<Long> {
        var nextPrimeContainer: Iterator<Long> = NaturalNumbersGreaterThan(1)

        override fun hasNext(): Boolean = true
        override fun next(): Long {
            val primeToReturn = nextPrimeContainer.next()
            nextPrimeContainer = ExcludeMultiples(primeToReturn, nextPrimeContainer)
            return primeToReturn
        }
    }
}
