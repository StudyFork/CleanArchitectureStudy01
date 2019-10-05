package com.beok.common.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerView {

    abstract class ViewHolder<VDB : ViewDataBinding>(
        @LayoutRes
        private val resource: Int,
        parent: ViewGroup,
        private val bindingId: Int?
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(
                resource,
                parent,
                false
            )
    ) {

        private val binding: VDB = DataBindingUtil.bind(itemView)!!

        fun onBindViewHolder(item: Any?) {
            if (bindingId == null) return
            if (item == null) return

            binding.run {
                setVariable(
                    bindingId,
                    item
                )
                executePendingBindings()
            }
        }
    }

    abstract class Adapter<A : Any, VDB : ViewDataBinding>(
        @LayoutRes
        private val layoutRes: Int,
        private val bindingId: Int?
    ) : RecyclerView.Adapter<ViewHolder<VDB>>() {

        private val items = mutableListOf<A>()

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder<VDB> = object : ViewHolder<VDB>(
            layoutRes,
            parent,
            bindingId
        ) {}

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(
            holder: ViewHolder<VDB>,
            position: Int
        ) = holder.onBindViewHolder(items[position])
    }
}