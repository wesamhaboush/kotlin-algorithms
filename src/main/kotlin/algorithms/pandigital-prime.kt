package algorithms

/*
Pandigital prime
Problem 41
We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital and is also prime.

What is the largest n-digit pandigital prime that exists?

solutions:

so, we can look for mutations of
1 to 9 // 1 + 2 + 3 + ... + 9 = 45, divides 3, so never a prime
1 to 8 // 1 + 2 + 3 + ... + 8 = 36, divides 3, so never a prime
1 to 7
1 to 6 // 1 + 2 + 3 + 4 + 5 + 6 = 21, divides 3, so is never prime
1 to 5 // 1 + 2 + 3 + 4 + 5 = 15, these divide by 3, so is never prime
1 to 4
1 to 3 // 1 + 2 + 3 = 6 these divide by 3, so is never prime
1 to 2 // 1 + 2 = 3, divides three, so never a prime

so only two simple range of mutations to look in.
        */

fun largestPrimePandigital(): Long =
        LexicographicPermutations(listOf(7, 6, 5, 4, 3, 2, 1))
                .asSequence()
                .map { digitsToNumber(it) }
                .first { isPrime(it) }
