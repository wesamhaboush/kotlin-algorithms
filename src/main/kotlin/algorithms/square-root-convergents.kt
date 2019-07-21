package algorithms

import algorithms.BigIntegers.countDigitsInNumber
import java.math.BigInteger
import java.math.BigInteger.ONE
import java.math.BigInteger.TWO

fun countMoreDigitsInNumerator(numberOfExpansions: Int): Int {
    if (numberOfExpansions < 0) {
        throw IllegalArgumentException("cannot accept negative numbers")
    }
// Haskell:
//
// main =
// print length filter (\(a,b) -> len a > len b) $ take 1000 terms
// where len = length . show
//
// terms = f (1,1)
// where f (a,b) = let next = (a+2*b,a+b)
// in next : f next
    var count = 0
    var numerator: BigInteger = ONE
    var denominator: BigInteger = ONE
    for (i in 0 until numberOfExpansions) {
        val previousNumerator = numerator
        val previousDenominator = denominator
        numerator = previousNumerator + TWO.times(previousDenominator)
        denominator = previousNumerator + previousDenominator
        if (countDigitsInNumber(numerator) > countDigitsInNumber(denominator)) {
            count++
        }
    }
    return count
}
