package com.david.hackro.kotlinext

import kotlin.math.ln
import kotlin.math.pow

private const val FORMAT = "%.1f %c"
private const val LONG_VALUES = "kMGTPE"
private const val ONE_THOUSAND = 1000
private const val ONE_THOUSAND_DOUBLE = 1000.0

fun Double.formatValue(): String {
    if (this < ONE_THOUSAND) return String.empty() + this
    val exp = (ln(this) / ln(ONE_THOUSAND_DOUBLE)).toInt()
    return java.lang.String.format(FORMAT, this / ONE_THOUSAND_DOUBLE.pow(exp.toDouble()), LONG_VALUES[exp - 1])
}
