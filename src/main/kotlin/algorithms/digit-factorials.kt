package algorithms

/*
Digit factorials

Problem 34
145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.

Find the sum of all numbers which are equal to the sum of the factorial of their digits.

Note: as 1! = 1 and 2! = 2 are not sums they are not included.
 */

fun sumFactorialOfDigits(number: Long): Long {
    val firstDigit: Long = number % 10
    val rest: Long = number / 10
    return if (rest == 0L) {
        factorial(firstDigit)
    } else {
        factorial(firstDigit) + sumFactorialOfDigits(rest)
    }
}

fun findAllNumbersEqualToSumOfDigitFactorials(): Long {
    val maximumNumber = generateSequence(9L) { 10 * it + 9 } // try 9, 99, 999, 9999, etc
            // find the first number with 9s that greater than its factorial sum
            // there is no hope after that the factorial would be equal
            // to the number itself
            .dropWhile { it < sumFactorialOfDigits(it) }
            .first()
            .toInt()

    return generateSequence(10L) { it + 1 }
            .take(maximumNumber)
            .filter { sumFactorialOfDigits(it) == it }
            .sum()
}
