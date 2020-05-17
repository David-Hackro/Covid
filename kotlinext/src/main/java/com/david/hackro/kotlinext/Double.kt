package com.david.hackro.kotlinext

import kotlin.math.ln
import kotlin.math.pow

fun Double.formatValue(): String {
    if (this < 1000) return "" + this
    val exp = (ln(this) / ln(1000.0)).toInt()
    return java.lang.String.format(
        "%.1f %c",
        this / 1000.0.pow(exp.toDouble()),
        "kMGTPE"[exp - 1]
    )
}
