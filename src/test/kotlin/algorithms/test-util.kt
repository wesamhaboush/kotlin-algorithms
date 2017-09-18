package algorithms

import kotlin.system.measureTimeMillis

fun time(block: () -> Unit) {
    val time = measureTimeMillis(block)
    println("it took: $time ms")
}
