package dev.daeyeon.common.ext

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["isVisible"])
fun View.isVisible(visible: Boolean?) {
    this.visibility = visible?.let {
        if (visible) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
    } ?: View.INVISIBLE
}

@BindingAdapter(value = ["isVisibleOrGone"])
fun View.isVisibleOrGone(visible: Boolean?) {
    this.visibility = visible?.let {
        if (visible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    } ?: View.GONE
}
