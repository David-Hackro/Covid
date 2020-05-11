package com.david.hackro.kotlinext

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun yesterday(format: String): String {
    val dateFormat = SimpleDateFormat(format)
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, -1)
    val yesterday = calendar.time
    return dateFormat.format(yesterday).toString()
}
