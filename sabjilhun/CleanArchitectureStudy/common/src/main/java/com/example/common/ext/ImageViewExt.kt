package com.example.common.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.common.utils.TextUtils

@BindingAdapter("uri")
fun ImageView.setImageWithUri(uri: String) {
    if (TextUtils.isEmpty(uri)) {
        return
    }

    Glide.with(this)
        .load(uri)
        .into(this)
}