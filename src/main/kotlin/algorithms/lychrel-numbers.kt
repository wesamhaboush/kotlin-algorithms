package algorithms

import kotlin.math.max

/*
Lychrel numbers
Problem 55
If we take 47, reverse and addAsNumbers, 47 + 74 = 121, which is palindromic.

Not all numbers produce palindromes so quickly. For example,

349 + 943 = 1292,
1292 + 2921 = 4213
4213 + 3124 = 7337

That is, 349 took three iterations to arrive at a palindrome.

Although no one has proved it yet, it is thought that some numbers, like 196, never produce a palindrome.
A number that never forms a palindrome through the reverse and addAsNumbers process is called a Lychrel number.
Due to the theoretical nature of these numbers, and for the purpose of this problem, we shall assume that a number
is Lychrel until proven otherwise. In addition you are given that for every number below ten-thousand, it will
either (i) become a palindrome in less than fifty iterations, or, (ii) no one, with all the computing power that
exists, has managed so far to map it to a palindrome. In fact, 10677 is the first number to be shown to require over
fifty iterations before producing a palindrome: 4668731596684224866951378664 (53 iterations, 28-digits).

Surprisingly, there are palindromic numbers that are themselves Lychrel numbers; the first example is 4994.

How many Lychrel numbers are there below ten-thousand?

NOTE: Wording was modified slightly on 24 April 2007 to emphasise the theoretical nature of Lychrel numbers.


solution discussion:

if we create a map from number to its next and enrich so that we can keep track of which chains we faced,
then we can end calculations early (no repetition of calculations). Then for each number starting at the top (10000
down to 1), we find the fifty iterations (or palindromic end).

actually we create two maps, one for starts that end with palindroms and another for starts that
do not end with palindrom, and we check these for palindromicness.

Seems we just need to sets, ones that ended up in palindromes, and ones that did not. And as we are finding
nexts, we add the ones that ended up with no palindromes to the fail list, and the other to the not success
list.
 */

fun lychrelNumbersBelow(max: Long): List<List<Int>> {
    val lychrelStarts = mutableSetOf<List<Int>>()
    val nonLychrelStarts = mutableSetOf<List<Int>>()
    generateSequence(0L) { it + 1 }
            .take(max.toInt())
            .forEach {
                val currentSet = mutableSetOf<List<Int>>()
                val currentNumberAsDigits = numberToDigits(it)
                currentSet.add(currentNumberAsDigits)
                val palindromicList = generateSequence(reverseAndAddDigits(currentNumberAsDigits)) { reverseAndAddDigits(it) }
                        .onEach { currentSet.add(it) }
                        .take(50) // max 50 iterations
                        .takeWhile { !lychrelStarts.contains(it) } // once you found a Lychrel, stop work
                        // look for either palindromic, or something known to be (seen before and it ends palindromic)
                        .find { isPalindromic(it) || nonLychrelStarts.contains(it) }
                        // otherwise, this is a Lychrel, we could not find the list, return it.
                        ?: emptyList()

                if(palindromicList.isNotEmpty()) { // classify the number we worked through
                    nonLychrelStarts.addAll(currentSet)
                } else {
                    lychrelStarts.addAll(currentSet)
                }
            }
    val maxAsDigits = numberToDigits(max)
    return lychrelStarts
            .sortedWith(Comparator({ t1, t2 -> compareDigitLists(t1, t2)}))
            .filter { compareDigitLists(it, maxAsDigits) < 0 }
}

fun reverseAndAddDigits(number: List<Int>): List<Int> = addAsNumbers(listOf(number, number.reversed()))


fun addAsNumbers(listOfLists: List<List<Int>>): List<Int> {
    val numberOfDigitsToSum = listOfLists
            .map { it.size }
            .max() ?: 0

    val result = mutableListOf<Int>()

    // for numbers of different sizes, we do the following:
    // 1 2 3 4 5
    //     7 6 4
    //         9
    // first is the largest number in terms of digit count, so number of digits will be 5, so i = 4 to start with.
    // so, from first number we take it[4],
    // from second we take it[4 - (5 - 3)], which is it[2]
    // from third we take it[4 - (5 - 1)], which is it[0], and not if we end up with it[-1], we should return zero
    // so the general fromula is: [i - (max - it.size)]
    var i = numberOfDigitsToSum - 1 // point to first digit of longest number
    var carry = 0
    while (i >= 0 || carry != 0) {
        var sum = listOfLists.map {
            val compensation = numberOfDigitsToSum - it.size
            if (i - compensation < 0)
                0
            else
                it[i - compensation]
        }.sum() + carry

        // take only first digit from the sum and the rest goes to carry (for example, take 7 from 187, so carry will be 18)
        carry = sum / 10
        // take only first digit from the sum and the rest goes to carry (for example, take 7 from 187)
        sum %= 10
        //note: this is in the wrong direction, but we reverse at the end!!
        result.add(sum)
        // next digit to handle
        i--
    }
    return result.reversed()
}

fun compareDigitLists(number1: List<Int>, number2: List<Int>): Int {
    val max = max(number1.size, number2.size)
    val firstNumberOffset = max - number1.size
    val secondNumberOffset = max - number2.size
    return (0 until  max).asSequence()
            .map { number1.elementAtOrElse(it - firstNumberOffset, { 0 }).compareTo(number2.elementAtOrElse(it - secondNumberOffset, { 0 })) }
            .firstOrNull { it != 0 }?:0
}


fun isLychrelWithin50(number: Long): Long = generateSequence(reverseAndAddDigits(numberToDigits(number))) { reverseAndAddDigits(it) }
        .take(50)
        .filter { isPalindromic(it) }
        .map { digitsToNumber(it) }
        .firstOrNull()?:-1

fun lychrelNumbersBelowNaive(max: Long): Set<Long> = (0..max).asSequence()
        .filter { isLychrelWithin50(it) == -1L }
        .toSortedSet()
