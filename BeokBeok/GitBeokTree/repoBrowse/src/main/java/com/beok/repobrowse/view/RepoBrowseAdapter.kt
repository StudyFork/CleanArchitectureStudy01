package com.beok.repobrowse.view

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.beok.common.base.BaseRecyclerView
import com.beok.repobrowse.databinding.RvRepoFiletreeItemBinding
import com.beok.repobrowse.viewmodel.RepoBrowseViewModel

class RepoBrowseAdapter<A : Any, VDB : ViewDataBinding>(
    @LayoutRes
    private val layoutRes: Int,
    private val bindingId: Int,
    private val vm: RepoBrowseViewModel
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
            if (binding is RvRepoFiletreeItemBinding) {
                binding.vm = this@RepoBrowseAdapter.vm
            }
        }
    }

}