package algorithms

import java.io.File

fun readDictionary(): Sequence<String> =
        File(ClassLoader.getSystemResource("words.txt").file)
                .bufferedReader()
                .lineSequence()
