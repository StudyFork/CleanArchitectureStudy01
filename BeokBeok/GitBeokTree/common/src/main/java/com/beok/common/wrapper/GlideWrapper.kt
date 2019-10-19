package com.beok.common.wrapper

import android.content.Context
import android.widget.ImageView
import com.beok.common.R
import com.bumptech.glide.Glide

object GlideWrapper {

    fun loadImage(
        context: Context,
        url: String,
        targetView: ImageView
    ) {
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.img_github_holder)
            .into(targetView)
    }

}