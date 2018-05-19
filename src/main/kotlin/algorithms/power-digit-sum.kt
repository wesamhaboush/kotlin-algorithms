package algorithms

/*
Powerful digit sum
Problem 56

A googol (10^100) is a massive number: one followed by one-hundred zeros; 100^100 is almost unimaginably
 large: one followed by two-hundred zeros. Despite their size, the sum of the digits in each number
 is only 1.

Considering natural numbers of the form, a^b, where a, b < 100, what is the maximum digital sum?

Solution:

I have two lines of discovery:
- the easy one is to do the powering using multiplicatin manaully like below.
- the second look for an equation that produces some kind of pattern or equation using some
mathematical laws. so far i have failed to find such solution

In order to improve performance and to explore the inner of the problem
a little better, let's see what information we can gather.
According to Problem 25, the number length function
L(n)=⌊1+log10(n)⌋
and therefore L(a^b)=⌊1+log10(a^b)⌋=⌊1+b⋅log10(a)⌋.

The upper limit is then 9⋅L(ab)
and if we say all digits are uniformly distributed, the expected
sum of the digits would be 5⋅L(ab), which gives
9⋅L(99^99)=9⋅198=1782, 5⋅L(99^99)=5⋅198=999 and
similarly 9⋅L(90^90)=9⋅176=1584, 5⋅L(90^90)=5⋅176=880.

Since the expected value raises dramatically from 880 to 990 in
this small interval, it's probably safe to start with a=90 and
b=90:

'a' can't be 10 multiply cz it will be full of zeros

So here is the search solution:

basically, we are keeping track of the max we found so far. We are looping downwards
until the maximum possible sum (9 times number of digits) can never be higher than the
current sum. For example, if we found a sum that is 1100, and the maximum digit sum for
7^7 is 900, there is no need to go down any further. This scheme saved us ~70% of the search
space

*/

object PowerDigitSum {

    fun sumDigitsOfExponent(base: Int, exp: Int): Int {
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

        return digits
                .map { it }
                .sum()
    }

    fun maxPowerDigitSum(maxBase: Int, maxExp: Int): Int {
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

    fun digitCountOf(base: Int, exp: Int): Int = Math.floor(1 + exp * Math.log10(base.toDouble())).toInt()
    private fun maxDigitSum(base: Int, exp: Int): Int = 9 * digitCountOf(base, exp)

}
