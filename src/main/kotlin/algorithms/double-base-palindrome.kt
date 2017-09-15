package algorithms

/*
Double-base palindromes
Problem 36
The decimal number, 585 = 1001001001 (binary), is palindromic in both bases.

Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.

(Please note that the palindromic number, in either base, may not include leading zeros.)
 */

fun isBinaryPalindromic(number: Long): Boolean {
    return isPalindromic(number.toString(2).toList())
}

fun isDecimalPalindromic(number: Long): Boolean {
    return isPalindromic(numberToDigits(number))
}

fun findPalindromicNumbersInBothBinaryAndDecimalBelow(max: Int): Sequence<Long> {
    return generateSequence(0L) { it + 1 }
            .takeWhile { it < max }
            .filter { isDecimalPalindromic(it) && isBinaryPalindromic(it)  }
}


