package com.beok.githubreposearch.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.beok.githubreposearch.base.BaseRecyclerView

@Suppress("UNCHECKED_CAST")
@BindingAdapter("replaceItems")
fun RecyclerView.replaceItems(item: List<Any>?) {
    (this.adapter as? BaseRecyclerView.BaseAdapter<Any, *>)?.run {
        replaceItems(item)
        notifyDataSetChanged()
    }
}