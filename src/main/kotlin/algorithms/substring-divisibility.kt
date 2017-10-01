package algorithms

/*
Sub-string divisibility
Problem 43
The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some order, but it also has a rather interesting sub-string divisibility property.

Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:

d2d3d4=406 is divisible by 2
d3d4d5=063 is divisible by 3
d4d5d6=635 is divisible by 5
d5d6d7=357 is divisible by 7
d6d7d8=572 is divisible by 11
d7d8d9=728 is divisible by 13
d8d9d10=289 is divisible by 17
Find the sum of all 0 to 9 pandigital numbers with this property.

Solution:

Note that:
d2d3d4 must divide by 2, so location d4 has to be one of the following: 0, 2, 4, 6, 8
d3d4d5 must divide by 3,
d4d5d6 must divide by 5, so location d6 has to be one of the following: 0 or 5
d5d6d7 must divide by 7,
d6d7d8 must divide by 11,
d7d8d9 must divide by 13,
d8d9d10 must divide by 17,
 */

val digits = (0..9).toSet()

fun findAllValidPermutations(): Set<List<Int>> =
        // first digit can be anything
        findNthValue(1, emptyList(), emptyList())
                // second digit can be anything except first digit (note: first and second parameters are sham,
                // ie. just to allow any digit except first to pass through
                .flatMap { D1 -> findNthValue(1, D1, D1) }
                // third digit can be anything except first or second digits
                // (note: first and second parameters are sham,
                // ie. just to allow any digit except first and second chosen digits to pass through
                .flatMap { D1D2 -> findNthValue(1, D1D2, D1D2) }
                // fourth digit has to be even, so we do not care about previous ones, we just care about
                // the digit to be chosen to equal one of 0, 2, 4, 6, 8
                .flatMap { D1D2D3 -> findNthValue(2, emptyList(), D1D2D3) }
                // fifth digit has to make d3d4d5 divide by 3, so we pass the last 2 digits to be summed with the
                // chosen one.
                .flatMap { D1D2D3D4 -> findNthValue(3, last2ElementsOf(D1D2D3D4), D1D2D3D4) }
                // sixth digit has to make d4d5d6 divide by 5, i.e. can only be 0 or 5.
                // Note this step filters a lot, cz all numbers that used both 0 and 5 in previous digits
                // are filtered out
                .flatMap { D1D2D3D4D5 -> findNthValue(5, last2ElementsOf(D1D2D3D4D5), D1D2D3D4D5) }
                // seventh digit has to make d5d6d7 divide by 7, so we pass the previous 2 (d5d6).
                .flatMap { D1D2D3D4D5D6 -> findNthValue(7, last2ElementsOf(D1D2D3D4D5D6), D1D2D3D4D5D6) }
                // eighth digit has to make d6d7d8 divide by 11, so we pass the previous 2 (d6d7).
                .flatMap { D1D2D3D4D5D6D7 -> findNthValue(11, last2ElementsOf(D1D2D3D4D5D6D7), D1D2D3D4D5D6D7) }
                // nineth digit has to make d7d8d9 divide by 13, so we pass the previous 2 (d7d8).
                .flatMap { D1D2D3D4D5D6D7D8 -> findNthValue(13, last2ElementsOf(D1D2D3D4D5D6D7D8), D1D2D3D4D5D6D7D8) }
                // 10th digit has to make d8d9d10 divide by 17, so we pass the previous 2 (d8d9).
                .flatMap { D1D2D3D4D5D6D7D8D9 -> findNthValue(17, last2ElementsOf(D1D2D3D4D5D6D7D8D9), D1D2D3D4D5D6D7D8D9) }
                .toSet()

private fun last2ElementsOf(inputList: List<Int>) =
        inputList.subList(inputList.size - 2, inputList.size)

private fun findNthValue(divisor: Int,
                 nPreviousDigits: List<Int>,
                 digitsUsedSoFar: List<Int>): Set<List<Int>> =

        digits.minus(digitsUsedSoFar)
                .filter { digitsToNumber(nPreviousDigits + it).toInt() % divisor == 0 }
                .map { nextDigit -> digitsUsedSoFar + nextDigit }
                .toSet()


