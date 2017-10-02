package algorithms

/*
Pentagon numbers
Problem 44
Pentagonal numbers are generated by the formula,

P_n=n(3n−1)/2.

The first ten pentagonal numbers are:

1, 5, 12, 22, 35, 51, 70, 92, 117, 145, ...

It can be seen that P4 + P7 = 22 + 70 = 92 = P8.

However, their difference, 70 − 22 = 48, is not pentagonal.

Find the pair of pentagonal numbers, Pj and Pk, for which their sum and difference are pentagonal
and D = |Pk − Pj| is minimised; what is the value of D?

Solution:

P_n=n(3n−1)/2

n (3n - 1) = 2P_n
3n^2 - n = 2P_n
3n^2 - n - 2P_n = 0

we know that:
x = (-b +- sqrt(b^2 - 4ac))/2a

so:
a = 3, b = -1, c = -2P_n

n =  (1 +- sqrt(1 + 4 * 3 * 2 * P_n)) / 6
  =  (1 +- sqrt(1 + 24 * P_n)) / 6

  so, to use a brutal solutions, we start by looking at the following algorithm:

  * for each pentagonal number N
  * for each pentagonal number M < N, starting from the first M below N and moving downwards
  * calculate different and sum and test for pentagonal-numberness
  * if not found for N, move on to N+1, and repeat the whole thing until you find your target.

 */

fun findTargetPentagonalPair(): Pair<Pair<Int, Int>, Pair<Int, Int>> =
        // for each pentagonal number Pk
        pentagonalNumbers()
                // get all the Pj pentagonal numbers below it starting from the one just below it
                // note, we start from top down here to minimize the difference
                .flatMap { maxPn ->
                    pentagonalNumbersDownFrom(maxPn.first)
                            .map { Pair(maxPn, it) }
                }
                // find those ones that have both sum and difference to be also pentagonal
                .filter { it ->
                    val difference = it.first.second - it.second.second
                    val sum = it.first.second + it.second.second
                    isPentagonalNumber(difference) && isPentagonalNumber(sum)
                }
                // we need the first of those only
                .first()


// give the infinite sequence of pentagonal numbers P(1), P(2), P(3), ...
fun pentagonalNumbers(): Sequence<Pair<Int, Int>> =
        generateSequence(1) { it + 1 }
                .map { Pair(it, pentagonalNumber(it)) }

// give a sequence of pentagonal numbers starting from P(n), P(n - 1), P(n - 2), ..., P(1).
fun pentagonalNumbersDownFrom(n: Int): Sequence<Pair<Int, Int>> =
        generateSequence(n) { it - 1 }
                .takeWhile { it > 0 }
                .map { Pair(it, pentagonalNumber(it)) }

// simply the equation for a pentagonal number P(n)
fun pentagonalNumber(n: Int): Int = n * (3 * n - 1) / 2

fun isPentagonalNumber(pn: Int): Boolean {
    // this check does two things based on the following equation from above:
    //    n =  (1 +- sqrt(1 + 4 * 3 * 2 * P_n)) / 6
    //    =  (1 +- sqrt(1 + 24 * P_n)) / 6
    // it checks the sqrt is integer,
    // and it checks the whole value of nominator divides by 6
    val decidingTerm = 1L + 24 * pn

    return isPerfectSquare(decidingTerm) && (Math.sqrt(decidingTerm.toDouble()).toInt() - 5) % 6 == 0
}