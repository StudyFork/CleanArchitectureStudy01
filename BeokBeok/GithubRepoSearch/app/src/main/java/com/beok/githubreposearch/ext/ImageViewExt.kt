package com.beok.githubreposearch.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.beok.githubreposearch.Wrapper.GlideWrapper

@BindingAdapter("srcWithGlide")
fun ImageView.srcWithGlide(url: String) {
    GlideWrapper.loadImage(
        this.context,
        url,
        this
    )
}