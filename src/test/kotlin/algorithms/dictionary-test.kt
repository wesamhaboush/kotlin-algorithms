package algorithms

import org.junit.Test

class `dictionary-test` {

    @Test
    fun test1() {
        val chars = listOf('y', 'u', 'o', 'w', 'd', 's')
        val noChars = listOf(
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        ).subtract(chars)

        time {
            readDictionary2()
                    .map { it.toLowerCase() }
                    .filter { it.length in 3..8 }
                    .filter { frequenyMap(it.filter { it != 'd' }).all { it.value < 2 } }
                    .filter { it.toList().count { chars.contains(it) } > 3 }
                    .filter { it.toList().count { noChars.contains(it) } == 0 }
                    .onEach { println(it) }
                    .toList()
        }
    }

    fun frequenyMap(word: String): Map<Char, Int> =
            word.toList()
                    .groupingBy { it }
                    .eachCount()
}
