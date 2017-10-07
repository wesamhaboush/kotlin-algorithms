package algorithms

import kotlin.reflect.KFunction1

/*
Triangular, pentagonal, and hexagonal
Problem 45
Triangle, pentagonal, and hexagonal numbers are generated by the following formulae:

Triangle	 	Tn=n(n+1)/2	 	1, 3, 6, 10, 15, ...
Pentagonal	 	Pn=n(3n−1)/2	 	1, 5, 12, 22, 35, ...
Hexagonal	 	Hn=n(2n−1)	 	1, 6, 15, 28, 45, ...
It can be verified that T285 = P165 = H143 = 40755.

Find the next triangle number that is also pentagonal and hexagonal.

Solution:

assume tn = (1, 2), hn = (1, 3), pn = (1, 4) // all different
assume tn = (1, 2), hn = (1, 2), pn = (1, 2) // all same
assume tn = (1, 2), hn = (1, 2), pn = (1, 3) // 1 greater than rest
assume tn = (1, 2), hn = (1, 2), pn = (1, 1) // 1 less than rest

we generally want to:
1- if all is different, move the ones that are smaller than max to be equal to or greater than max
2- if all the same, advance them all by 1
3- if 1 is greater than two equal ones, move both smaller ones to until they are equal to or greater than the greatest
not that this is the same as the max
4- if 1 is less than 2 other, advance it until it is same or greater than the max

Notes:
for equation: ax + by = c
if gcd(a,b) does not divide c, there is no integer solutions.


Note: we can drop the triangular work because every hexagonal number is a triangular number as well.
 */

//triangular hexadecimal, and pentagonal
data class Thp(val tn: Pair<Long, Long>, val hn: Pair<Long, Long>, val pn: Pair<Long, Long>)

// generate a sequence of values that have all of them equal with the n for each type
fun triangularHexagonalPentagonals(): Sequence<Thp> =
        generateSequence(Thp(Pair(1, 1), Pair(1, 1), Pair(1, 1))) {
            val maximum = maxOf(it.tn.second, it.hn.second, it.pn.second)
            // if all the same, then we already found one, and now trying to find the next,
            // so, let's move them all along
            if (it.tn.second == maximum && it.hn.second == maximum && it.pn.second == maximum) {
                Thp(
                        Pair(it.tn.first + 1, triangular(it.tn.first + 1)),
                        Pair(it.hn.first + 1, hexagonal(it.hn.first + 1)),
                        Pair(it.pn.first + 1, pentagonal(it.pn.first + 1))
                )
            } else {
                // if not all the same, try to move the smaller ones until they either equal or pass
                // the max. We might get lucky and find the next all equal, or otherwise, we repeat
                Thp(
                        advanceToMax(it.tn.first, ::triangular, maximum),
                        advanceToMax(it.hn.first, ::hexagonal, maximum),
                        advanceToMax(it.hn.first, ::pentagonal, maximum)
                )
            }
        }
                .filter { allEqual(it.tn.second, it.hn.second, it.pn.second) }

fun advanceToMax(n: Long, advance: KFunction1<Long, Long>, maximum: Long): Pair<Long, Long> =
    generateSequence(Pair(n, advance(n))) { Pair(it.first + 1, advance(it.first + 1)) }
            .filter { it.second >= maximum }
            .first()


fun allEqual(n1: Long, n2: Long, n3: Long): Boolean = n1 == n2 && n1 == n3

fun triangularHexagonalPentagonals2(): Sequence<Long> =
        generateSequence(1L) { it + 1 }
                .map { hexagonal(it) }
                .filter { isPentagonal(it) }