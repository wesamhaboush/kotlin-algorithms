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
        private var nextPrime = 2L

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
                var t = 2L
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
        generateSequence(1L) { it + 1 }
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

fun <T> powerSet(input: Collection<T>): Set<Set<T>> = powerSet(input, setOf(setOf()))

private tailrec fun <T> powerSet(left: Collection<T>, acc: Set<Set<T>>): Set<Set<T>> = when {
    left.isEmpty() -> acc
    else -> powerSet(left.drop(1), acc + acc.map { it + left.first() })
}

object Combinations {
    //really need a bag, but have to use a list cz java does not have a bag implementation
    //and i am lazy looking for one
    fun <T> of(items: List<T>): Set<List<T>> {
        return ofInternal({ _ -> true }, items)
    }

    fun <T> of(size: Int, items: List<T>): Set<List<T>> {
        return ofInternal({ it.size == size }, items)
    }

    private fun <T> ofInternal(sizeFilter: (Set<Int>) -> Boolean, items: List<T>): Set<List<T>> {
        val indices = generateSequence(0) { it + 1 }
                .take(items.size).toSet()

        return powerSet(indices)
                .filter{ sizeFilter(it) }
                .map{ toItems(items, it) }
                .toSet()
    }

    private fun <T> toItems(items: List<T>, indices: Set<Int>): List<T> {
        return indices.asSequence()
                .map { items[it] }
                .toList()
    }
}

fun sumDigitLists(digitLists: Collection<List<Int>>): List<Int>
{
    return digitLists.reduce(::findSum)
}

fun findSum(firstDigitList: List<Int>, secondDigitList: List<Int>): List<Int> {

    val sortedBySize = listOf(firstDigitList, secondDigitList).sortedBy { it.size }

    val smallerDigitList = sortedBySize.first()
    val largerDigitList = sortedBySize.last()

    val result = mutableListOf<Int>()

    // Calculate lenght of both string
    val n1 = smallerDigitList.size
    val n2 = largerDigitList.size
    val diff = n2 - n1

    // Initialy take carry zero
    var carry = 0

    // Traverse from end of both strings
    for (i in n1 - 1 downTo 0) {
        // Do school mathematics, compute sum of
        // current digits and carry
        val sum = smallerDigitList[i] + (largerDigitList[i + diff]) + carry
        result.add(0, sum % 10)
        carry = sum / 10
    }

    // Add remaining digits of larger list
    for (i in n2 - n1 - 1 downTo 0) {
        val sum = largerDigitList[i] + carry
        result.add(0, sum % 10)
        carry = sum / 10
    }

    // Add remaining carry
    if (carry > 0)
        result.add(0, carry)

    return result
}
