package algorithms

/*
Distinct primes factors
Problem 47
The first two consecutive numbers to have two distinct prime factors are:

14 = 2 × 7
15 = 3 × 5

The first three consecutive numbers to have three distinct prime factors are:

644 = 2² × 7 × 23
645 = 3 × 5 × 43
646 = 2 × 17 × 19.

Find the first four consecutive integers to have four distinct prime factors each. What is the first of these numbers?
 */

fun uniqPrimeFactors(n: Long): Set<Long> = primeFactors(n).toSet()

fun firstNConsecutiveIntegersWithNDistinctPrimeFactors(n: Int): List<Long> =
        generateSequence(1L) { it + 1 }
                .map { listOfNumbers(it, n) }
                .filter { allHasUniquePrimeFactorsOfSize(it, n) }
                .first()

private fun allHasUniquePrimeFactorsOfSize(it: List<Long>, numberOfPrimeFactors: Int) =
        it.all { uniqPrimeFactors(it).size == numberOfPrimeFactors }

private fun listOfNumbers(startFrom: Long, howMany: Int): List<Long> =
        (startFrom until (startFrom + howMany)).toList()
