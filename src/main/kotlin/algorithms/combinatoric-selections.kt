package algorithms

/*
Combinatoric selections
Problem 53
There are exactly ten ways of selecting three from five, 12345:

123, 124, 125, 134, 135, 145, 234, 235, 245, and 345

In combinatorics, we use the notation, 5C3 = 10.

In general,

nCr =	n! / r!(n−r)! where r ≤ n, n! = n x (n−1) x ... x 3 x 2 x 1, and 0! = 1.
It is not until n = 23, that a value exceeds one-million: 23C10 = 1144066.

How many, not necessarily distinct, values of  nCr, for 1 ≤ n ≤ 100, are greater than one-million?

Solution Analysis:

use pascal triangle row calculation to find out those numbers.
 */

fun countCombinationsAboveMaxForAllN(n: Int, max: Long): Int = getPascalRows2(n)
        // some times the numbers in the middle of the triangle are so huge, Longs are not enough ,we get negative values
        // so the idea here is not to just count, but rather simply find the boundaries (between them all entries must match),
        // because the triangle is symmetric, you can double that that does not match or match from the other side
        // we could also use variables can hold arbitrary numerical values if we want to!!
        .map {
            val indexOfFirst = it.indexOfFirst { it > max }
            if (indexOfFirst < 0) { // if element not found, then we have 0 matches
                0
            } else {
                it.size - 2 * indexOfFirst // we double cz end of beginning of the row have same values (e.g. 1, 2, 2, 3, 3, 2, 2, 1)
            }
        }
        .sum()
