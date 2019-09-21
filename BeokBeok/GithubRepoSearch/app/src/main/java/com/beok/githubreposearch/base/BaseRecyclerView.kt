package com.beok.githubreposearch.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseRecyclerView {

    open class BaseViewHolder<VDB : ViewDataBinding>(
        @LayoutRes
        private val layoutRes: Int,
        parent: ViewGroup,
        private val bindingId: Int?
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(
                layoutRes,
                parent,
                false
            )
    ) {

        private val binding: VDB = DataBindingUtil.bind(itemView)!!

        fun onBind(item: Any?) {
            if (bindingId != null) {
                binding.setVariable(
                    bindingId,
                    item
                )
            }
        }
    }

    open class BaseAdapter<A : Any, VDB : ViewDataBinding>(
        @LayoutRes
        private val layoutRes: Int,
        private val bindingId: Int?
    ) : RecyclerView.Adapter<BaseViewHolder<VDB>>() {

        private val items = mutableListOf<A>()

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): BaseViewHolder<VDB> = object : BaseViewHolder<VDB>(
            layoutRes,
            parent,
            bindingId
        ) {}

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(
            holder: BaseViewHolder<VDB>,
            position: Int
        ) = holder.onBind(items[position])
    }

}