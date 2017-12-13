import algorithms.isPrime
import algorithms.primes
import algorithms.sublistsOfSize

/*Consecutive prime sum
Problem 50
The prime 41, can be written as the sum of six consecutive primes:

41 = 2 + 3 + 5 + 7 + 11 + 13

This is the longest sum of consecutive primes that adds to a prime below one-hundred.

The longest sum of consecutive primes below one-thousand that adds to a prime, contains 21 terms,
and is equal to 953.

Which prime, below one-million, can be written as the sum of the most consecutive primes?


Solution:
find the list of primes less than 1000000
find if any sum of a subsequence of size n is a prime, then go to n - 1, etc until you found one, then you
found the largest one
        */

fun primesLessThan(n: Long): List<Long> = primes(n)
        .asSequence()
        .toList()

fun primeAsSumOfLongestConsecutiveSequence(max: Long): Pair<List<Long>, Long> {
    val maxNumberOfTermsAndThePrimes = numberOfPrimesBeforeSumIsLargerThan(max)
    val listOfPrimes = maxNumberOfTermsAndThePrimes.second
    val maxLength = listOfPrimes.size

    return generateSequence(maxLength) { it - 1 }
            .takeWhile { it > 0 } // try all sequence lengths from n, n - 1, n - 2, ...., 1
//            .onEach { println("trying sequence length: ${it}") }
            .map { sublistsOfSize(listOfPrimes, it) } // find sublists of n size
            .flatMap { it.map { Pair(it, it.sum()) } } // find the sums of those sublists
            .filter { it.second < max && isPrime(it.second) } // look for ones that sum to a prime
//            .onEach { println(it) }
            .first() // return the first of these
}

fun numberOfPrimesBeforeSumIsLargerThan(max: Long): Pair<Int, List<Long>> = generateSequence(1) { it + 1 }
        .map { primesLessThan(it.toLong()) }
        .map { Pair(it.size, it.toList()) }
        .filter { it.second.sum() > max }
        .first()
