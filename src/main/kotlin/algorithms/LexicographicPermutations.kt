package algorithms


/*
The following algorithm generates the next permutation lexicographically after a given permutation. It changes the given permutation in-place.

Find the largest index k such that a[k] < a[k + 1]. If no such index exists, the permutation is the last permutation.
Find the largest index l greater than k such that a[k] < a[l].
Swap the value of a[k] with that of a[l].
Reverse the sequence from a[k + 1] up to and including the final element a[n].
 */

class LexicographicPermutations<out T>(private val items: List<T>) : Iterator<List<T>> {

    private var indices: Array<Int> = (0 until items.size)
            .toList()
            .toTypedArray()

    private var isFirstCall = true

    override fun hasNext(): Boolean {
        //either it is first time, or we check for next
        return isFirstCall || isNextPermutationPossible(indices)
    }

    override fun next(): List<T> {
        if (isFirstCall) {
            isFirstCall = false
            return toList()
        } else {
            if (!hasNext())
                throw NoSuchElementException("No Further Permutations From here")


            indices = getNextPermutation(indices)
            return toList()
        }
    }

    private fun toList(): List<T> {
        return indices.fold<Int, List<T>>(mutableListOf()) { list, index ->
            list + items[index]
        }
    }
}

private fun isNextPermutationPossible(array: Array<Int>): Boolean {
    // Find non-increasing suffix
    var i = array.size - 1
    while (i > 0 && array[i - 1] >= array[i])
        i--
    return i > 0
}

private fun getNextPermutation(array: Array<Int>): Array<Int> {
    // Find non-increasing suffix
    var i = array.size - 1
    while (i > 0 && array[i - 1] >= array[i])
        i--

    // Find successor to pivot
    var j = array.size - 1
    while (array[j] <= array[i - 1])
        j--
    var temp = array[i - 1]
    array[i - 1] = array[j]
    array[j] = temp

    // Reverse suffix
    j = array.size - 1
    while (i < j) {
        temp = array[i]
        array[i] = array[j]
        array[j] = temp
        i++
        j--
    }
    return array
}
