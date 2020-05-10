package com.david.hackro.kotlinext

fun String.Companion.empty() = ""

fun String?.safeString() = this ?: String.empty()
