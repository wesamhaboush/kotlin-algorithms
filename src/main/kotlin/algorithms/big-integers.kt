package algorithms

import java.math.BigInteger
import java.util.stream.IntStream
import kotlin.streams.toList

object BigIntegers {
    private val lengths = IntStream.range(1, 2000) // algorithm fails if numbers have more than 2000 digits
            .boxed()
            .map { Length(it) }
            .toList()

    fun countDigitsInNumber(number: BigInteger): Int {
        return lengths.stream()
                .filter { it.isLengthOf(number.abs()) }
                .findFirst()
                .map { it.value() }
                .orElseThrow()
    }

    private class Length(private val value: Int) {
        private val tenPowerLengthMax: BigInteger = BigInteger.valueOf(10).pow(value)

        fun isLengthOf(number: BigInteger): Boolean {
            // all numbers less than 10  (10 ^ 1 ) have length = 1
            // all numbers less than 100 (10 ^ 2) have length = 2
            // etc
            return number < tenPowerLengthMax
        }

        fun value(): Int {
            return value
        }
    }

    fun bi(n: Int): BigInteger {
        return BigInteger.valueOf(n.toLong())
    }
}
