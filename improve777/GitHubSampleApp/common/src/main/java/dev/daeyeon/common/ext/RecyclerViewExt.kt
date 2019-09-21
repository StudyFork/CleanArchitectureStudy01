package dev.daeyeon.common.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.daeyeon.common.base.SimpleRecyclerView

/**
 * https://github.com/sjjeong/dino-library/blob/master/src/main/kotlin/com/dino/library/ext/RecyclerViewExt.kt
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter("replaceAll")
fun RecyclerView.replaceAll(list: List<Any>?) {
    (this.adapter as? SimpleRecyclerView.Adapter<Any, *>)?.replaceAll(list)
    (this.adapter as? SimpleRecyclerView.ListAdapter<Any, *>)?.submitList(list)
}