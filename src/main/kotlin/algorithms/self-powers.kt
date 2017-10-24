package algorithms

/*
Self powers
Problem 48
The series, 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317.

Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.


These are the characteristics of modulo that we may use

(a*b) % c = ((a % c) * (b % c) )% c

and

(a+b) % c = ((a % c) + (b % c) )% c.


 */

fun firstNDigitsOf(numberOfDigits: Int, maxN: Int): Long {
    val divider = intPow(10, numberOfDigits)
    return generateSequence(1) { it + 1 }
            .take(maxN)
            .map { moduloPower(it, it, divider) }
            .sum() % divider
}

fun intPow(a: Long, b: Int): Long =
    generateSequence(a) { it }
            .take(b)
            .reduce { acc, value -> acc * value }

fun moduloPower(a: Int, b: Int, mod: Long): Long =
    generateSequence(a) { it }
            .take(b)
            .map { it.toLong() }
            .reduce { acc, value -> moduloMultiply(acc, value, mod) }

fun moduloMultiply(a: Long, b: Long, c: Long): Long = ((a % c) * (b % c)) % c

fun moduloSum(a: Long, b: Long, c: Long): Long = ((a % c) + (b % c)) % c
