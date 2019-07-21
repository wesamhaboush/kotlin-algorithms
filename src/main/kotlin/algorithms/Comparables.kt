package algorithms

object Comparables {
    fun <T: Comparable<T>> lessThan(n1: T, n2: T): Boolean = n1 < n2
}
