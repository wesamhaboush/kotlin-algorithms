package algorithms

import org.junit.Test

class `dictionary-test` {

    @Test
    fun test1() {
        val chars = listOf('d', 'i', 'z', 'r', 'o', 's')
        val noChars = listOf(
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        ).subtract(chars)

        time {
            readDictionary2()
                    .map { it.toLowerCase() }
                    .filter { it.length in 3..8 } // minimum and maximum number of letters
                    .filter { frequenyMap(it.filter { it != 'r' }).all { it.value < 2 } } // frequency of letter with exceptions
                    .filter { it.toList().count { chars.contains(it) } > 3 } // how many correct letters
//                    .filter { it.toList().count { noChars.contains(it) } == 0 } // how many different chars
                    .filter { it.toList().containsAll(listOf('m', 'o')) } // must letters
                    .filter { !it.toList().any { it in listOf('v', 't', 'a', 'p', 'n', 'e') } } // forbidden letters
                    .onEach { println(it) }
                    .toList()
        }
    }

    fun frequenyMap(word: String): Map<Char, Int> =
            word.toList()
                    .groupingBy { it }
                    .eachCount()
}
