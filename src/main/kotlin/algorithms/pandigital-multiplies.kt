package algorithms

import java.lang.Math.floor
import java.lang.Math.pow

/*

Pandigital multiples
Problem 38
Take the number 192 and multiply it by each of 1, 2, and 3:

192 × 1 = 192
192 × 2 = 384
192 × 3 = 576
By concatenating each product we get the 1 to 9 pandigital, 192384576. We will call 192384576 the concatenated product of 192 and (1,2,3)

The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5, giving the pandigital, 918273645, which is the concatenated product of 9 and (1,2,3,4,5).

What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer with (1,2, ... , n) where n > 1?

Solution:

any sum that has more or less digits than 9 is toast
any sum that has repetion of any kind is toast

largest number that can be tested is: 999 999 999
we cannot have more than 9 of n, i.e. 1 x (1, 2, 3, 4, 5, 6, 7, 8, 9)

Note that: no repetition is allowed
once you have a number that's not pandigital and

*/

fun maxPandigitalMultiply(): Long {
    return generateSequence(1L) { it + 1 }
            .take( floor(pow(10.0, 5.0)).toInt() ) //only 4 digits number or less count cz (1, 2) x XXXXX
            // if it contains zero, it cannot ever be pan-digital
            // if it has repetitions, impossible to be pan-digital
            .filter {
                val digits = numberToDigits(it)
                !digits.contains(0) && digits.toSet().size == digits.size
            }
            .flatMap { allConcatenatedProductsOf(it) }
            .max()?:0L
}

fun allConcatenatedProductsOf(number: Long): Sequence<Long> {
    // get (1, 2), (1, 2, 3), (1, 2, 3, 4), etc
    val countDigitsInNumber = countDigitsInNumber(number)
    return sequenceOfWideningIntegerWindow()
            //until count of digits gone above the 9 limit
            .takeWhile { it.size * countDigitsInNumber <= 9 } // can't fit 1,2,3,4 and 192 for example
            .map { toConcatenatedProductDigitList(it, number) }
            .takeWhile { it.size <= 9 } // once you've crossed 9 digits, you've gone too far
            // to get max, use the 9 prefix
            // pan-digital has to divide by 9
            // has to then be pan-digital
            .filter { it[0] == 9 && it.sum() % 9 == 0 && isPandigitalList(it) }
            .map { toPandigitalNumber(it) }
}

fun toPandigitalNumber(digitList: List<Int>): Long {
    return digitsToNumber(digitList)
}

val allDigits = (1..9).toList()

fun isPandigitalList(digitList: List<Int>): Boolean {
    return digitList.size == 9 && digitList.containsAll(allDigits)
}

fun toConcatenatedProductDigitList(intList: List<Int>, number: Long): List<Int> {
//    println("handling [$number][$intList]")
    return intList.fold(listOf()) { list, i -> list + numberToDigits(i * number) }
}

fun sequenceOfWideningIntegerWindow(): Sequence<List<Int>> {
    return generateSequence(2) { it + 1 }
            .map { (1..it).toList() }
}
