package com.david.hackro.kotlinext

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

private const val YESTERDAY = -1

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun getCurrentDateTime(): Date = Calendar.getInstance().time

fun yesterday(format: String): String {
    val dateFormat = SimpleDateFormat(format)
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, YESTERDAY)
    val yesterday = calendar.time
    return dateFormat.format(yesterday).toString()
}
