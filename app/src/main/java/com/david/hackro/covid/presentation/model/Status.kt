package com.david.hackro.covid.presentation.model

import com.david.hackro.covid.R

enum class STATUS { CONFIRMED, RECOVERED, DEATHS, ACTIVE }

fun STATUS.getIconByStatus() = when (this) {
    STATUS.CONFIRMED -> R.drawable.ic_confirmed
    STATUS.RECOVERED -> R.drawable.ic_heart
    STATUS.DEATHS -> R.drawable.ic_death
    STATUS.ACTIVE -> R.drawable.ic_active
}

fun STATUS.getDescriptionByStatus() = when (this) {
    STATUS.CONFIRMED -> R.string.confirmed
    STATUS.RECOVERED -> R.string.recovered
    STATUS.DEATHS -> R.string.deaths
    STATUS.ACTIVE -> R.string.actived
}

fun STATUS.getColorByStatus() = when (this) {
    STATUS.CONFIRMED -> R.color.confirmed
    STATUS.RECOVERED -> R.color.recovered
    STATUS.DEATHS -> R.color.deaths
    STATUS.ACTIVE -> R.color.active
}

fun STATUS.getBackgroundByStatus() = when (this) {
    STATUS.CONFIRMED -> R.drawable.item_background_confirmed
    STATUS.RECOVERED -> R.drawable.item_background_recovered
    STATUS.DEATHS -> R.drawable.item_background_deaths
    STATUS.ACTIVE -> R.drawable.item_background_active
}
