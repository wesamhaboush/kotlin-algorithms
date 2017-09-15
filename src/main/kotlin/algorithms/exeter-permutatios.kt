package algorithms

import java.util.*

object ExeterPermutation {
    private fun <T> permute(items: List<T>,
                            start: Int): List<List<T>> {
        val permutations = ArrayList<List<T>>()
        if (start == items.size - 1) {
            permutations.add(ArrayList(items))
        } else {
            for (i in start until items.size) {
                Collections.swap(items, i, start)
                permutations.addAll(permute(items, start + 1))
                Collections.swap(items, i, start)
            }
        }
        return permutations
    }


    fun <T> of(items: List<T>): List<List<T>> {
        return permute(ArrayList(items), 0) // create a mutable list
    }

}
