package sort

import java.lang.Double.NaN

object CountingSort {

    fun createSortingPlace(array: Array<Int>) : Array<Int> {
        return Array((array.max() ?: array.size) + 1) { 0 }
    }

    fun countNumberOccurrences(sortedArray: Array<Int>, sortingPlace: Array<Int>) {
        sortedArray.forEach { value ->
            addOccurrence(value, sortingPlace)
        }
    }

    fun addOccurrence(value: Int, sortingPlace: Array<Int>) {
        if (value < sortingPlace.size && value >= 0)
            sortingPlace[value]++
    }

    fun summarizeOccurrences(sortingPlace: Array<Int>) {
        sortingPlace.forEachIndexed { index, value ->
            summarizeOccurrence(index, sortingPlace)
        }
    }

    fun summarizeOccurrence(position: Int, sortingPlace: Array<Int>) {
        if (position > 0 && position < sortingPlace.size)
            sortingPlace[position] += sortingPlace[position - 1]
    }

    fun fillingResult(sortedArray: Array<Int>, sortingPlace: Array<Int>) {
        val buffArray = Array(sortedArray.size) { 0 }
        sortedArray.forEachIndexed { index, item ->
            val reverseIndex = sortedArray.size - index - 1
            val reverseItem = sortedArray[reverseIndex]
            val targetIndex = findTargetPosition(reverseItem, sortingPlace)
            targetIndex?.let { buffArray[it] = reverseItem }
        }
        buffArray.forEachIndexed { index, item ->
            sortedArray[index] = item
        }
    }

    fun findTargetPosition(value: Int, sortingPlace: Array<Int>) : Int? {
        return if (value >= 0 && value < sortingPlace.size) {
            val targetPosition = sortingPlace[value] - 1
            sortingPlace[value] = targetPosition
            targetPosition
        } else {
            null
        }
    }

    fun sort(sortedArray: Array<Int>) {
        val sortingPlace = createSortingPlace(sortedArray)
        countNumberOccurrences(sortedArray, sortingPlace)
        summarizeOccurrences(sortingPlace)
        fillingResult(sortedArray, sortingPlace)
    }

}