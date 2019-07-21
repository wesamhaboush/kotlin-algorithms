package algorithms

import java.io.File

fun readDictionary(): Sequence<String> =
        File(ClassLoader.getSystemResource("words.txt").file)
                .bufferedReader()
                .lineSequence()

fun readDictionary2(): Sequence<String> =
        File(ClassLoader.getSystemResource("words-550000.txt").file)
                .bufferedReader()
                .lineSequence()
