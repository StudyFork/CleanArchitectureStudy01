package com.beok.reposearch.presenter

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.beok.common.base.BaseRecyclerView
import com.beok.reposearch.databinding.RvRepoItemBinding

class RepoSearchAdapter<A : Any, VDB : ViewDataBinding>(
    @LayoutRes
    private val layoutRes: Int,
    private val bindingId: Int,
    private val vm: RepoSearchViewModel
) : BaseRecyclerView.Adapter<A, VDB>(
    layoutRes,
    bindingId
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerView.ViewHolder<VDB> = ViewHolder(parent)

    inner class ViewHolder(
        parent: ViewGroup
    ) : BaseRecyclerView.ViewHolder<VDB>(
        layoutRes,
        parent,
        bindingId
    ) {

        override fun onBindViewHolder(item: Any?) {
            super.onBindViewHolder(item)
            if (binding is RvRepoItemBinding) binding.vm = this@RepoSearchAdapter.vm
        }

    }
}