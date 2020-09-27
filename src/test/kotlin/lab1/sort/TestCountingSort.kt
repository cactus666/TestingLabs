package sort

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import sort.CountingSort.addOccurrence
import sort.CountingSort.createSortingPlace
import sort.CountingSort.findTargetPosition
import sort.CountingSort.summarizeOccurrence

class TestCountingSort {

    private lateinit var testStartSortingPlace: Array<Int>
    private lateinit var testSortingPlace: Array<Int>

    companion object {
        private lateinit var testSortedArray: Array<Int>

        @BeforeAll
        @JvmStatic
        internal fun setupData() {
            testSortedArray = arrayOf(20, 25, 17, 21, 20, 20, 11, 24, 21, 5, 28, 26, 23, 27, 21, 22, 3, 13, 1, 3, 13, 7, 21, 12, 25, 2, 4)
        }
    }

    @BeforeEach
    fun createSortingPlace() {
        testStartSortingPlace = arrayOf(0, 0, 0)
        testSortingPlace = createSortingPlace(testSortedArray)
    }

    @Test
    fun testSortingPlace() {
        val sortingPlace = createSortingPlace(testSortedArray)
        assertEquals(29, sortingPlace.size, "Размер массива, в котором подсчитывается количество вхождений, не верен (не равен максимальному числу + 1).")
        sortingPlace.forEach {
            assertEquals(0, it, "Массив, в котором подсчитывается количество вхождений, не заполнен 0.")
        }
    }

    @Test
    fun testAddOccurrenceNormalInterval() {
        addOccurrence(0, testStartSortingPlace)
        assertArrayEquals(arrayOf(1, 0, 0), testStartSortingPlace, "При подсчете количества вхождений не учитываются положительные числа входящие в нужный диапозон.")
    }

    @Test
    fun testAddOccurrenceBelowInterval() {
        addOccurrence(-1, testStartSortingPlace)
        assertArrayEquals(arrayOf(0, 0, 0), testStartSortingPlace, "При подсчете количества вхождений учитываются отрицательные числа.")
    }

    @Test
    fun testAddOccurrenceAboveInterval() {
        addOccurrence(3, testStartSortingPlace)
        assertArrayEquals(arrayOf(0, 0, 0), testStartSortingPlace, "При подсчете количества вхождений учитываются положительные числа выходящие за пределы в нужного диапозона сверху.")
    }

    @Test
    fun testSummarizeOccurrencePositionInNormalInterval() {
        addOccurrence(0, testStartSortingPlace)
        addOccurrence(2, testStartSortingPlace)
        addOccurrence(0, testStartSortingPlace)
        addOccurrence(1, testStartSortingPlace)
        val position = 1
        val countItem = testStartSortingPlace[position]
        val countPreItem = testStartSortingPlace[position - 1]
        summarizeOccurrence(position, testStartSortingPlace)
        assertTrue(testStartSortingPlace[position] == countPreItem + countItem, "Метод по подсчету вхождений(с учетом предыдущих значений) работает не корректно для индексов входящих в необходимый диапозон.")
    }

    @Test
    fun testSummarizeOccurrencePositionInStartInterval() {
        addOccurrence(0, testStartSortingPlace)
        addOccurrence(2, testStartSortingPlace)
        addOccurrence(0, testStartSortingPlace)
        addOccurrence(1, testStartSortingPlace)
        summarizeOccurrence(0, testStartSortingPlace)
        assertTrue(testStartSortingPlace[0] == 2, "Метод по подсчету вхождений(с учетом предыдущих значений) работает не корректно для индекса 0.")
    }

    @Test
    fun testSummarizeOccurrencePositionInBelowInterval() {
        addOccurrence(0, testStartSortingPlace)
        addOccurrence(2, testStartSortingPlace)
        addOccurrence(0, testStartSortingPlace)
        addOccurrence(1, testStartSortingPlace)
        val preStartSortingPlace = testStartSortingPlace.copyOf()
        summarizeOccurrence(-1, testStartSortingPlace)
        assertArrayEquals(testStartSortingPlace, preStartSortingPlace, "Метод по подсчету вхождений(с учетом предыдущих значений) работает не корректно для отрицательных индексов.")
    }

    @Test
    fun testSummarizeOccurrencePositionInAboveInterval() {
        addOccurrence(0, testStartSortingPlace)
        addOccurrence(2, testStartSortingPlace)
        addOccurrence(0, testStartSortingPlace)
        addOccurrence(1, testStartSortingPlace)
        val preStartSortingPlace = testStartSortingPlace.copyOf()
        summarizeOccurrence(testStartSortingPlace.size, testStartSortingPlace)
        assertArrayEquals(testStartSortingPlace, preStartSortingPlace, "Метод по подсчету вхождений(с учетом предыдущих значений) работает не корректно для индексов, превышающих максимально допустимый индекс.")
    }

    @Test
    fun findTargetPositionInNormalIntervalForExistentElement() {
        CountingSort.countNumberOccurrences(testSortedArray, testSortingPlace)
        CountingSort.summarizeOccurrences(testSortingPlace)
        assertEquals(3, findTargetPosition(3, testSortingPlace), "Поиск позиции, куда нужно поместить число выдает не верный результат.")
    }

    @Test
    fun findTargetPositionInNormalIntervalForNotExistentElement() {
        CountingSort.countNumberOccurrences(testSortedArray, testSortingPlace)
        CountingSort.summarizeOccurrences(testSortingPlace)
        assertEquals(5, findTargetPosition(6, testSortingPlace), "Поиск позиции, куда нужно поместить не существующее число выдает не верный результат.")
    }

    @Test
    fun findTargetPositionInBelowInterval() {
        CountingSort.countNumberOccurrences(testSortedArray, testSortingPlace)
        CountingSort.summarizeOccurrences(testSortingPlace)
        assertEquals(null, findTargetPosition(-1, testSortingPlace), "Поиск позиции для отрицательных чисел находит значение не равное null.")

    }

    @Test
    fun findTargetPositionInAboveInterval() {
        CountingSort.countNumberOccurrences(testSortedArray, testSortingPlace)
        CountingSort.summarizeOccurrences(testSortingPlace)
        assertEquals(null, findTargetPosition(29, testSortingPlace), "Поиск позиции для чисел, превышающих максимально допустимое число, находит значение не равное null.")
    }

}