package algorithms

/*
Prime digit replacements
Problem 51
By replacing the 1st digit of the 2-digit number *3, it turns out that six of
the nine possible values: 13, 23, 43, 53, 73, and 83, are all prime.

By replacing the 3rd and 4th digits of 56**3 with the same digit, this 5-digit number
is the first example having seven primes among the ten generated numbers, yielding
the family: 56003, 56113, 56333, 56443, 56663, 56773, and 56993. Consequently 56003,
being the first member of this family, is the smallest prime with this property.

Find the smallest prime which, by replacing part of the number (not necessarily
adjacent digits) with the same digit, is part of an eight prime value family.

Solutions:

Look at primes that have same numbers, and replace the same numbers with all digits.
Note, you'll have to try replacing 2 digits, then 3, etc until you reach the number of max similar digits.

If we focus on only two digit replacements just like the example although it says
replacing any number of digits!

For two digits to have a family of 8, then the prime cannot be with duplicates of any number greater than 2
because then it would not complete the family to an 88 members. Let's try the max:
22,33,44,55,66,77,88,99

 */


fun findFamilyOfPrimesOfSize(familySize: Int) = primesWithDuplicateDigits(familySize)
//generate from this is: 770986, 880986, and 990986, so let's exclude this one
//for example, if prime is: 770986, and family size is 4, we cannot use this one, cz the best we can
//filter ones that have duplicates that are less than 10 - familySize!!

        // now find the family and check it is the right size
        .map { Triple(primesFromReplacements(it), it.second, it.third) }
        .filter { it.first.size >= familySize }

fun primesFromReplacements(initialPrimeInFamily: Triple<Long, List<Int>, Pair<Int, List<Int>>>): List<Long> =
        sequenceOf(initialPrimeInFamily.first)
                .plus(
                        // start with the number after the current prime, for example, if prime is 277, we start with 288
                        generateSequence(initialPrimeInFamily.third.first + 1) { it + 1 }
                                // we go from digit to 9, for example: 266 will go: 266 277 288, then 299, so the family has a size of 4
                                .takeWhile { it < 10 }
                                .map { replacement ->
                                    initialPrimeInFamily.second.mapIndexedTo(mutableListOf(),
                                            { index, element -> if (initialPrimeInFamily.third.second.contains(index)) replacement else element })
                                }.map { digitsToNumber(it.toList()) } //after replacing, convert to a number
                                .filter { isPrime(it) } /*only primes join family*/
                ).toList()

private fun onlyDigitsSmallEnoughToAllowFamilySize(it: Int, familySize: Int) = it < (10 - familySize)


fun primesWithDuplicateDigits(familySize: Int): Sequence<Triple<Long, List<Int>, Pair<Int, List<Int>>>> = primes(1000000)
        .asSequence()
        .map { primeNumber ->
            val digits = numberToDigits(primeNumber)
            Triple(primeNumber, digits, occurrenceMap(digits))
        }
        // break primes with multiple multi occurence of digits into multiple entries:
        // For example: triple (2211, [2,2,1,1], [2 -> [0, 1], 1 -> [2, 3])
        // will yield two tripes:
        // triple ([2211,[2,2,1,1], [2 -> [0, 1]])
        // triple ([2211,[2,2,1,1], [1 -> [2, 3]])
        .flatMap { triple ->
            triple.third
                    .filter { it.value.size > 1 } // a digit that happens more than once (ignore 0 or 1 occurrence digits)
                    .map { Triple(triple.first, triple.second, Pair(it.key, it.value)) } //break each repetition on its own triple
                    //now we need to break the above into even further triples based on the combination of indices that we can use
                    //for example:
                    //triple (111, [1,1,1], (1, [0, 1, 2])
                    //will yield:
                    //triple (111, [1,1,1], (1, [0, 1])
                    //triple (111, [1,1,1], (1, [0, 2])
                    //triple (111, [1,1,1], (1, [1, 2])
                    //triple (111, [1,1,1], (1, [0, 1, 2])
                    .flatMap { breakIntoMultipleBasedOnIndexCombinations(it) }
                    // note: the filter here checks the first few indices of the array because those are the digits
                    .filter { onlyDigitsSmallEnoughToAllowFamilySize(it.third.first, familySize) }
                    .asSequence()
        }

private fun breakIntoMultipleBasedOnIndexCombinations(inputTriple: Triple<Long, List<Int>, Pair<Int, List<Int>>>): List<Triple<Long, List<Int>, Pair<Int, List<Int>>>> =
        powerSet((0 until inputTriple.third.second.size).toSet())
                .filter { it.size > 1 } // no empty or one combination, we want repetition, so minimum is 2
                .map {
                    Triple(
                            inputTriple.first,
                            inputTriple.second,
                            Pair(
                                    inputTriple.third.first,
                                    it.map { inputTriple.third.second[it] } //map from combination to actual values
                            )
                    )
                }


fun occurrenceMap(list: List<Int>): Map<Int, List<Int>> = list.foldIndexed(
        Array(10, { _ -> 0 }).withIndex()
                .associateByTo(mutableMapOf<Int, MutableList<Int>>(),
                        { v -> v.index },
                        { _ -> mutableListOf() }
                ),
        { index, occurrenceMap, digit ->
            occurrenceMap.getOrPut(digit, { mutableListOf() }).add(index)
            occurrenceMap
        }
)
