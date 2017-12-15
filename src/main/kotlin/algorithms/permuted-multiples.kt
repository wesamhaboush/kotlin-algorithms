package algorithms

/*
Permuted multiples
Problem 52
It can be seen that the number, 125874, and its double, 251748, contain exactly the same digits,
but in a different order.

Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.

Solution notes:

- because it must divide by 2 at some point, we also know that it must contain one of 2, 4, 6, or 8
- because it must divide by 3 at some point, we know the sum must divide by 3
- because it must divide by 4, the first two digits must divide by 4 (
04 and 02, => [0, 2, 4]
08 and 04, => [0, 4, 8]
12 and 06, => [0, 1, 2, 6]
16 and 08, => [0, 1, 6, 8]
20 and 10, => [0, 1, 2]
24 and 12, => [1, 2, 4]
28 and 14, => [1, 2, 4, 8]
32 and 16, => [1, 2, 3, 6]
36 and 18, => [1, 3, 6, 8]
40 and 20, => [0, 2, 4] //repeat
not 44,
48 and 24, => [2, 4, 8]
52 and 26, => [2, 5, 6]
56 and 28, => [2, 5, 6, 8]
60 and 30, => [0, 3, 6]
64 and 32, => [2, 3, 4, 6]
68 and 34, => [3, 4, 6, 8]
72 and 36, => [2, 3, 6, 7]
76 and 38, => [3, 6, 7, 8]
80 and 40, => [0, 4, 8] // repeat
84 and 42, => [2, 4, 8] // repeat
not 88,
92 and 46, => [2, 4, 6, 9]
96 and 48  => [4, 6, 8, 9]
so it has to have two of the following (0, 2, 4, 6, 8)
- because this number must divide by 5 at some point, it must contain the number (0 or 5)
- because it must divide by 6, it has to divide by 2 and 3
- we need a number where (digit count in 6x) = (digit count in x)
so, we should use these rules to select the set of digits


interesting clever solution:
No coding for this one just remembered that the fractions 1/7, 2/7, 3/7, ... , 6/7 had the same digits
in different orders. Took the repeating digits of 1/7 and done!
 */

val evens = setOf(0, 2, 4, 8)

fun findNumber(): Sequence<Triple<Long, List<Long>, List<Int>>> = generateSequence(1L) { it + 1 }
        // count number must be equal
        .filter { countDigitsInNumber(it) == countDigitsInNumber(6 * it) }
        // number must divide by 3
        .filter { divides3(it) }
        .map { Pair(it, numberToDigits(it)) }// number must contain 0 or 5
        .filter { it.second.contains(0) || it.second.contains(5) }
        // number must contain two even numbers at least
        .filter { it.second.count { evens.contains(it) } >= 2 }
        .map {
            Triple(
                    it.first,
                    listOf(
                            it.first * 2,
                            it.first * 3,
                            it.first * 4,
                            it.first * 5,
                            it.first * 6
                    ),
                    it.second)
        }
//        .onEach { println("attempting ${it}") }
        .filter {
            val baseNumberDigitSet = it.third.toSet()
            it.second.map { digitSet(it) }.all { baseNumberDigitSet == it }
        }


fun digitSet(number: Long): Set<Int> = numberToDigits(number).toSet()
