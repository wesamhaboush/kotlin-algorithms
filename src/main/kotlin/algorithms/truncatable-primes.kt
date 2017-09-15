package algorithms

/*
Truncatable primes

Problem 37

The number 3797 has an interesting property. Being prime itself, it is possible to
continuously remove digits from left to right, and remain prime at each stage: 3797, 797, 97,
and 7. Similarly we can work from right to left: 3797, 379, 37, and 3.

Find the sum of the only eleven primes that are both truncatable from left to right and right
to left.

NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.

Solution:

The idea is that the possible numbers in such a truncatable have to be one of the following:
3, 5, 7, 9
So, we can build a tree from these, as in, start with possible first digit
3, 7 (can't be 9, cz that will make it non prime)
now add another digits in a tree to make it prime:
(3, 7), (3, 9), (3, 3), (7, 3), (7, 9), (7, 9)
now, drop all what is not prime, and repeat expanding these. Check each prime for its truncatable from
the other side.

Once you have 11 such primes, you are done.

Another way is to iterate over primes checking their truncatability.
 */

fun sumOfRightAndLeftTruncatablePrimes(): Long {
    return primes(1000000).asSequence()
            //TODO: take until you find 10 matches
            .filter { it > 20 } // nothing under 20 is truncatable cz it will have 1 as a truncation
            .filter { generateNumbers(it).all { isPrime(it) } }
//            .onEach { println("$it -> ${generateNumbers(it)}") }
            .take(11)
            .sum()
}

private fun generateNumbers(number: Long): List<Long> {
    val digits = numberToDigits(number)
    val rightDigits = (1 until digits.size).map { digitsToNumber(digits.take(it)) }
    val leftDigitis = (1 until digits.size).map { digitsToNumber(digits.takeLast(it)) }
    return (rightDigits + leftDigitis).toList()
}
