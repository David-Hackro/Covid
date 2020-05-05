package com.david.hackro.kotlinext

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun getCurrentDateTime(): Date {
    return Calendar.getInstance().time
}

fun yesterday(format: String): String {
    val dateFormat = SimpleDateFormat(format)
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, -1)
    val yesterday = calendar.time
    return dateFormat.format(yesterday).toString()
}
