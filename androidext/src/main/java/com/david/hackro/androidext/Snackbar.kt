package com.david.hackro.androidext

import android.content.Context
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.snackbar(text: CharSequence?, duration: Int = Snackbar.LENGTH_SHORT) =
    Snackbar.make(view!!, text ?: "", duration).apply { show() }

fun Context.snackbar(view: View, @StringRes resId: Int, duration: Int = Snackbar.LENGTH_SHORT) =
    Snackbar.make(view, resId, duration).apply { show() }

fun Snackbar.showSuccess() = this.apply { view.backgroundColor(R.color.success) }

fun Snackbar.showWarning() = this.apply { view.backgroundColor(R.color.warning) }

fun Snackbar.showError() = this.apply { view.backgroundColor(R.color.error) }

fun Snackbar.showNeutral() = this.apply { view.backgroundColor(R.color.neutral) }
