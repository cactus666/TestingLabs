package lab1.math

import kotlin.math.abs

object TrigonometricFunction {

    const val ACCURACY = 1E-6

    fun cos(x: Double, eps: Double = ACCURACY): Double {
        if (x.isInfinite() || x.isNaN() || eps.isInfinite() || eps.isNaN())
            return Double.NaN

        var iteration = 1
        var result = 0.0
        var part = 1.0

        while (abs(part) > eps && !part.isInfinite()) {
            result += part
            part *= -x * x / (2 * iteration * (2 * iteration - 1))
            iteration++
        }
        return result
    }

}