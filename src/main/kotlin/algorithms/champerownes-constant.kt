package algorithms

/*
Champernowne's constant
Problem 40
An irrational decimal fraction is created by concatenating the positive integers:

0.12345678910>1<112131415161718192021...

It can be seen that the 12th digit of the fractional part is 1.

If dn represents the nth digit of the fractional part, find the value of the following expression.

d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000

Solution:

There are,
 9 numbers with 1 digit,=9 digits of your sequence;
90 with 2 digits,=90*2=180 digits of your sequence;
900 with 3 digits, =900*3=2700 digits of your sequence;
9000 with 4 digits,=9000*4=36000 digits of your sequence.
90000 with 5 digits,.....so on

For finding nth digit, (explained by an example)
Let's take n=578,
Now there are 9+180=189 digits due to single and double digit numbers.
Remaining digits=578-189=389 .

Now you to just count 3 digit numbers starting from 100 (the first 3 digits number, cz 99 is the last 2 digits number).
389%3=129 remainder 2.

Current number (for nth digit)=100+129=229

So 578th digit is 2.

 */

fun champerownesDigitAtIndex(targetDigitIndex: Long): Int {
    var currentNumberOfDigits = 1L
    var currentIndex = 1L
    var nextBatchOfDigits = countNumbersWithDigitCount(currentNumberOfDigits) * currentNumberOfDigits

    while( (currentIndex + nextBatchOfDigits) < targetDigitIndex) {
        currentIndex += nextBatchOfDigits
        currentNumberOfDigits++
        nextBatchOfDigits = countNumbersWithDigitCount(currentNumberOfDigits) * currentNumberOfDigits
    }

    // once we are here, we are stopped at the step where the target index exists
    // let us find the first number in that step, which is 10^(currentNumberOfDigits)
    val firstNumberInCurrentStep = countNumbersWithDigitCount(currentNumberOfDigits) / 9
    // also find remaining digits to cover
    val remainingDigits = targetDigitIndex - currentIndex
    // let's find how many we need to count of the current number of digits:
    val numberOfCounts = remainingDigits / currentNumberOfDigits
    val indexOfTargetDigitWithinTargetNumber = remainingDigits % currentNumberOfDigits
    val targetNumber = firstNumberInCurrentStep + numberOfCounts

    return numberToDigits(targetNumber)[indexOfTargetDigitWithinTargetNumber.toInt()]
}

private fun countNumbersWithDigitCount(currentNumberOfDigits: Long) =
        9 * Math.pow(10.0, (currentNumberOfDigits - 1).toDouble()).toLong()
