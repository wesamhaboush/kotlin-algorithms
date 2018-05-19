package algorithms

import org.junit.Test

class `dictionary-test` {

    @Test
    fun test1() {
        val chars = listOf('u', 'd', 's', 'd', 'e', 'n')
        time {
            readDictionary()
                    .filter { it.length == 3 }
                    .filter { it.count { chars.contains(it) } == 3 }
                    .filter { listOf(2, 3).contains(it.toSet().size) }
                    .filter {
                        it.startsWith('d')
                            || it.startsWith('D')
                            || it.startsWith('e')
                            || it.startsWith('E')
                    }
                    .onEach { println(it) }
                    .toList()
        }
    }
}
