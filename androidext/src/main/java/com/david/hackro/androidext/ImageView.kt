package com.david.hackro.androidext

import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

fun ImageView.setImageVector(resId: Int) {
    setImageDrawable(ContextCompat.getDrawable(this.context, resId))
}

fun ImageView.setUrl(url: String) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}


fun ImageView.setUrlCircle(url: String) {
    Glide.with(this.context)
        .load(url)
        .circleCrop()
        .into(this)
}
