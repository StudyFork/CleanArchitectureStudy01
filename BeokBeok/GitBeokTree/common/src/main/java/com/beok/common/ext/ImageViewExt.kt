package com.beok.common.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.beok.common.wrapper.GlideWrapper

@BindingAdapter("srcWithGlide")
fun ImageView.srcWithGlide(url: String) {
    GlideWrapper.loadImage(
        this.context,
        url,
        this
    )
}