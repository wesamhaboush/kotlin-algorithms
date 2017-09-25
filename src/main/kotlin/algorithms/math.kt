package algorithms

import java.util.*

fun isPrime(number: Long): Boolean {
    if (number == 2L || number == 3L) return true
    if (number < 2) return false
    if (number % 2 == 0L) return false
    var i = 3
    while (i * i <= number) {
        if (number % i == 0L) return false
        i += 2
    }
    return true
}

fun allCircularNumbersFrom(number: Long): Set<Long> {
    val listOfDigits = numberToDigits(number)
    val circulars = allCirculationsOfList(listOfDigits)
    return circulars.map { digitsToNumber(it) }.toSet()
}

fun <T> allCirculationsOfList(input: List<T>): List<List<T>> {
    return (0 until input.size)
            .map { input.subList(it, input.size) + input.subList(0, it) }
            .toList()
}

fun divides3(number: Long) = number % 3 == 0L

fun countDigitsInNumber(number: Long): Int {
    return if (number == 0L) {
        1
    } else {
        (Math.log10(number.toDouble()) + 1.0)
                .toInt()
    }
}

fun numberToDigits(number: Long): List<Int> {
    var temp = number
    val array = ArrayList<Int>()
    do {
        array.add(0, (temp % 10).toInt())
        temp /= 10
    } while (temp > 0)
    return array.toList()
}

fun digitsToNumber(digits: List<Int>): Long {
    var result = 0L
    var multiplier = 1
    for (i in digits.indices.reversed()) {
        result += digits[i] * multiplier
        multiplier *= 10
    }
    return result
}

fun factorial(number: Long): Long {
    return if (number <= 1L) {
        1
    } else {
        number * factorial(number - 1)
    }
}


fun isPowerOf(number: Long, base: Long): Boolean {
    var current = number
    while (current % base == 0L) current /= base
    return current == 1L
}

fun <T> isPalindromic(list: List<T>): Boolean {
    require(list.isNotEmpty()) { "cannot test empty lists for a palindrome" }
    val n = list.size
    return (0..list.size / 2).all {
        list[it] == list[n - it - 1]
    }
}

fun toBase(decimal: Long, base: Long): Long {
    var runningDecimal = decimal
    var result = 0L
    var multiplier = 1

    while (runningDecimal > 0) {
        val residue = runningDecimal % base
        runningDecimal /= base
        result += residue * multiplier
        multiplier *= 10
    }
    return result
}

fun primes(max: Long): Iterator<Long> {
    return object : Iterator<Long> {
        private var nextPrime = 2L;

        override fun next(): Long {
            val prev = nextPrime
            nextPrime = nextPrime()
            return prev
        }

        override fun hasNext(): Boolean {
            return nextPrime < max
        }

        private fun nextPrime(): Long {
            while (true) {
                nextPrime++
                if (nextPrime <= 3)
                    return nextPrime
                var divisible = false
                var t = 2L;
                while (!divisible && t * t <= nextPrime) {
                    if (nextPrime % t == 0L)
                        divisible = true
                    t++
                }
                if (!divisible)
                    return nextPrime
            }
        }
    }
}

fun findFactors(number: Long): List<Pair<Long, Long>> =
    generateSequence (1L ){ it + 1 }
            .takeWhile { it < (1 + number / 2) } // no point checking more than half the number for factors
            .filter { number % it == 0L }
            .map { Pair(it, number / it) }
            .toList()

fun isPerfectSquare(n: Long): Boolean {
    return n >= 0 && when (n.and(0xF)) {
        0L, 1L, 4L, 9L -> isSquareRootPerfect(n)
        else -> false
    }
}

private fun isSquareRootPerfect(n: Long): Boolean {
    val nAsDouble = n.toDouble()
    val tst = Math.sqrt(nAsDouble).toLong()
    return tst * tst == n
}

fun isTriangularNumber(n: Long): Boolean = isPerfectSquare(8 * n + 1)
