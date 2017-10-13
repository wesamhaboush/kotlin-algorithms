package algorithms

/*
Goldbach's other conjecture
Problem 46
It was proposed by Christian Goldbach that every odd composite number can be written as
the sum of a prime and twice a square.

9 = 7 + 2×1^2
15 = 7 + 2×2^2
21 = 3 + 2×3^2
25 = 7 + 2×3^2
27 = 19 + 2×2^2
33 = 31 + 2×1^2

It turns out that the conjecture was false.

What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?
 */

fun compositeOdds(): Sequence<Long> =
        generateSequence(3L) { it + 2 }
                .filter { !isPrime(it) }


fun twiceSquares(): Sequence<Long> =
        generateSequence(1L) { it + 1 }
                .map { 2 * it * it }

fun canBeComposedOfPrimePlusTwiceSquare(number: Long): Boolean =
        twiceSquares()
                .takeWhile { it < number }
                .any { isPrime(number - it) }

fun firstOddCompositeBreakingGoldbachsOtherConjecture() =
        compositeOdds()
                .find { !canBeComposedOfPrimePlusTwiceSquare(it) }
