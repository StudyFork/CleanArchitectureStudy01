package com.beok.githubreposearch.wrapper

import android.content.Context
import android.widget.ImageView
import com.beok.githubreposearch.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object GlideWrapper {

    fun loadImage(
        context: Context,
        url: String,
        targetView: ImageView
    ) {
        Glide.with(context)
            .load(url)
            .apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher))
            .into(targetView)
    }
}