package com.beok.repobrowse.ext

import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter

@BindingAdapter("marginStartForFileTreeItem", "marginUnit")
fun View.setMarginStart(margin: Int, unit: Float) {
    (layoutParams as ViewGroup.MarginLayoutParams).let {
        it.marginStart = margin * unit.toInt()
        layoutParams = it
    }
}