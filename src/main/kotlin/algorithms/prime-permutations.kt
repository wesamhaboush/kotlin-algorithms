package algorithms

/*
Prime permutations
Problem 49
The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330,
is unusual in two ways:
(i) each of the three terms are prime, and,
(ii) each of the 4-digit numbers are permutations of one another.

There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property,
but there is one other 4-digit increasing sequence.

What 12-digit number do you form by concatenating the three terms in this sequence?
 */

//data class Thp(val tn: Pair<Long, Long>, val hn: Pair<Long, Long>, val pn: Pair<Long, Long>)


fun primesOf4Digits(): Sequence<Long> = primesBetween(1000, 9999).asSequence()

fun primesWith4SameDigits(): List<List<Long>> = primesOf4Digits()
        .map { Pair(it, numberToDigits(it) ) } // pair(number, its digits)
        .groupBy { it.second.sorted() } // group all the ones with the same 4 digits
        .map { it.value.map { it.first }.toList() } // extract those numbers with the same digits into lists

fun listsWithCharacteristics(): List<List<Long>> = primesWith4SameDigits()
        .flatMap { Combinations.of(3, it) }
//        .onEach { println(it) }
        .filter { withSameDistance(it) }
        .toList()

fun withSameDistance(numbers: List<Long>): Boolean = generateSequence(1) { it + 1 }
        .take(numbers.size - 1)
//        .onEach { println("${numbers} => ${numbers[it]} - ${numbers[it-1]} = ${numbers[it] - numbers[it - 1]}") }
        .map { numbers[it] - numbers[it - 1] }
        .toSet()
        .size == 1



