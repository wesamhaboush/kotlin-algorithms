package algorithms

import java.io.File

/*
Coded triangle numbers
Problem 42
The nth term of the sequence of triangle numbers is given by, t(n) = (1/2)(n)(n+1);
so the first ten triangle numbers are:

1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...

By converting each letter in a word to a number corresponding to its alphabetical position
and adding these values we form a word value. For example, the word value for SKY is
19 + 11 + 25 = 55 = t(10).

If the word value is a triangle number then we shall call the word a triangle word.

Using words.txt (right click and 'Save Link/Target As...'), a 16K text file containing nearly
two-thousand common English words, how many are triangle words?
 */

fun readFile(): Sequence<String> =
        File(ClassLoader.getSystemResource("p042_words.txt").file)
                .bufferedReader()
                .lineSequence()

fun fileAsWords(): Sequence<String> = readFile()
        .flatMap { it.split(',').asSequence() }

fun letterToNumber(letter: Char): Int = letter.toLowerCase().toInt() - 96

fun wordToNumber(word: String): Int = word.map { letterToNumber(it) }.sum()

fun countNumberOfTriangularWords(): Int = fileAsWords()
        .map { unquote(it) }
        .filter { isTriangularNumber(wordToNumber(it).toLong()) }
//        .onEach { println("word: $it, number: ${wordToNumber(it)}") }
        .count()

fun unquote(word: String) = word.substring(1, word.length - 1)
