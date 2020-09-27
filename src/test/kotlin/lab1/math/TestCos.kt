package lab1.math

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.math.PI

class TestCos {

    @Test
    fun testTopBorder(){
        assertEquals(1.0, TrigonometricFunction.cos(0.0), TrigonometricFunction.ACCURACY, "Верхняя граница вычисляется не верно. Тест: cos(2i * PI) = 1")
    }

    @Test
    fun testBottomBorder(){
        assertEquals(-1.0, TrigonometricFunction.cos(PI), TrigonometricFunction.ACCURACY, "Нижняя граница вычисляется не верно. Тест: cos((2i + 1) * PI) = -1")
    }

    @Test
    fun testXIntercept(){
        assertEquals(0.0, TrigonometricFunction.cos(-Math.PI / 2), TrigonometricFunction.ACCURACY, "Пересечение с осью ординат не верно. Тест: cos(-(2i + 1) * PI/2) = 0")
        assertEquals(0.0, TrigonometricFunction.cos(PI / 2), TrigonometricFunction.ACCURACY, "Пересечение с осью ординат не верно. Тест: cos((2i + 1) * PI/2) = 0")
    }

    @Test
    fun testRightPartOfThePositiveSegment() {
        assertEquals(0.5, TrigonometricFunction.cos(PI / 3), TrigonometricFunction.ACCURACY, "Правая часть положительного отрезка не верна. Тест: cos(2i * PI + PI/3) = 0.5")
    }

    @Test
    fun testRightPartOfTheNegativeSegment() {
        assertEquals(-0.5, TrigonometricFunction.cos(PI - PI / 3), TrigonometricFunction.ACCURACY, "Правая часть отрицательного отрезка не верна. Тест: cos((2i + 1) * PI + PI/3) = -0.5")
    }

    @Test
    fun testLeftPartOfThePositiveSegment() {
        assertEquals(0.5, TrigonometricFunction.cos(-PI / 3), TrigonometricFunction.ACCURACY, "Левая часть положительного отрезка не верна. Тест: cos(-2i * PI - PI/3) = 0.5")
    }

    @Test
    fun testLeftPartOfTheNegativeSegment() {
        assertEquals(-0.5, TrigonometricFunction.cos(-PI - PI / 3), TrigonometricFunction.ACCURACY, "Левая часть отрицательного отрезка не верна. Тест: cos(-(2i + 1) * PI - PI/3) = -0.5")
    }

    @Test
    fun testInvalidArgument() {
        assertEquals(Double.NaN, TrigonometricFunction.cos(Double.POSITIVE_INFINITY), "cos(+inf) = NaN")
        assertEquals(Double.NaN, TrigonometricFunction.cos(Double.NEGATIVE_INFINITY), "cos(-inf) = NaN")
        assertEquals(Double.NaN, TrigonometricFunction.cos(Double.NaN), "cos(NaN) = NaN")
    }

    @Test
    fun testInvalidAccuracy() {
        assertEquals(Double.NaN, TrigonometricFunction.cos(Math.PI / 3, eps = Double.POSITIVE_INFINITY), "cos(x) = NaN, eps = +inf")
        assertEquals(Double.NaN, TrigonometricFunction.cos(Math.PI / 3, eps = Double.NEGATIVE_INFINITY), "cos(x) = NaN, eps = -inf")
        assertEquals(Double.NaN, TrigonometricFunction.cos(Math.PI / 3, eps = Double.NaN), "cos(x) = NaN, eps = NaN")
    }

}