package algorithms


/*
Circular primes
Problem 35
The number, 197, is called a circular prime because all rotations of the digits:
197, 971, and 719, are themselves prime.

There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.

How many circular primes are there below one million?

The idea of the solution is to find the numbers in a much smaller search space. For example:
- No even digits are allowed, so 0, 2, 4, 6, 8 are not allowed to be in the numbers except the prime 2)
- The digit five is not allowed in the number (except the prime 5 itself
- Any set of digits with a sum that divides by 3 (except the prime 4 itself)
- Then we can test for primeness
*/

fun circularPrimes(upperBound: Long): Set<Long> {
    require(isPowerOf(upperBound, 10)) { "upperBound must be a power of 10" }

    // find how many digits can our number be:
    // for example, if input is 10000, then a max of 4 digits are required,
    // so we need to generate all 1 digit numbers (x), and two digits (xx), three digits(xxx)
    // and finally four digit numbers (xxxx)
    val maxNumberOfDigits = countDigitsInNumber(upperBound) - 1
    val circularPrimesSet =
            // we need to add the single digit numbers that are circular always
            // and the general rules do not apply to them
            sequenceOf(2L, 3L, 5L).filter { it < upperBound } + generateSequence(1) { it + 1 }
                    // we need to generate a sequence of digit couts, for example
                    // for 10000 upperBound, we need to generate (1, 2, 3, 4) sequence
                    // in order to generate their combinations
                    .take(maxNumberOfDigits)
                    //each number of digits value will generate a list of values
                    // so we need to flat map (from 1 digits -> List() + 2 digits -> List()) etc.
                    .flatMap {
                        // from each number of digits, generate the possible combinations
                        // for example, for 2 digits, we find all combinations from
                        // (1, 3, 7, 9) of 2 digits, ie. 13, 31, 79, 97, 37, 73, 19, 91, etc.
                        generateListOfPossibleCircularPrimesSequence(0, it)
                                .filter { !divides3(it) } // anything that divides by 3 is not prime
                                .filter { isPrime(it) } // anything that's not prime is toast
                                .filter {
                                    // now we find all cicle combinations from the number in question
                                    // and make sure they are all prime
                                    allCircularNumbersFrom(it).all { isPrime(it) }
                                }
                    }
    return circularPrimesSet.toSortedSet() // sorting it for beauty :)
}

private val allowedDigits = listOf(1, 3, 7, 9)

fun generateListOfPossibleCircularPrimesSequence(current: Long, numDigits: Int): Sequence<Long> {
    return if (numDigits == 0)
        sequenceOf(current)
    else
        allowedDigits
                .asSequence()
                .flatMap {
                    generateListOfPossibleCircularPrimesSequence(current * 10 + it, numDigits - 1)
                }
}



