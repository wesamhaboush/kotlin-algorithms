package algorithms

import java.math.BigInteger


/*
Powerful digit sum
Problem 56

A googol (10^100) is a massive number: one followed by one-hundred zeros; 100^100 is almost unimaginably
 large: one followed by two-hundred zeros. Despite their size, the sum of the digits in each number
 is only 1.

Considering natural numbers of the form, a^b, where a, b < 100, what is the maximum digital sum?

Solution:

'a' can't be 10 multiply cz it will be full of zeros

So here is the search solution:

basically, we are keeping track of the max we found so far. We are looping downwards
until the maximum possible sum (9 times number of digits) can never be higher than the
current sum. For example, if we found a sum that is 1100, and the maximum digit sum for
7^7 is 900, there is no need to go down any further. This scheme saved us ~70% of the search
space
*/
object PowerDigitSum {

    fun sumDigitsOfExponent(base: Int, exp: Int): Int =
            BigInteger(base.toString())
                    .pow(exp)
                    .toString()
                    .toCharArray()
                    .map { it - '0' }
                    .sum()

    fun sumDigitsOfExponentManual(base: Int, exp: Int): Int {
        val numberOfDigits = digitCountOf(base, exp)
        val digits = IntArray(numberOfDigits)
        digits[0] = base
        var currentExp = 1

        while (currentExp < exp) {
            currentExp++
            var carry = 0
            for (i in digits.indices) {
                val num = base * digits[i] + carry
                digits[i] = num % 10
                carry = num / 10
            }
        }

        return digits.sum()
    }

    fun maxPowerDigitSumOptimized(maxBase: Int, maxExp: Int): Int {
        var base = maxBase
        var maxSum = -1
        while (maxSum < maxDigitSum(base, maxExp)) { // base loop
            var exp = maxExp
            while (maxSum < maxDigitSum(base, exp)) { // exp loop
                maxSum = maxOf(maxSum, sumDigitsOfExponent(base, exp))
                exp -= 1
            }
            base -= 1
        }
        return maxSum
    }

    fun maxPowerDigitSumBruteforce(maxBase: Int, maxExp: Int): Int =
            generateSequence(1) { it + 1 }
                    .take(maxBase)
                    .flatMap { a ->
                        generateSequence(1) { it + 1 }
                                .take(maxExp)
                                .map { b ->
                                    sumDigitsOfExponent(a, b)
                                }
                    }
                    .max() ?: 0

    fun digitCountOf(base: Int, exp: Int): Int = Math.floor(1 + exp * Math.log10(base.toDouble())).toInt()
    private fun maxDigitSum(base: Int, exp: Int): Int = 9 * digitCountOf(base, exp)

}
