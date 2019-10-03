package com.example.search.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domains.entities.RepositorySummaryInfo
import com.example.search.R
import com.example.search.databinding.ItemRepositorySummaryInfoBinding

class RepositorySummaryInfoAdapter :
    RecyclerView.Adapter<RepositorySummaryInfoAdapter.ViewHolder>() {

    private var items: List<RepositorySummaryInfo> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_repository_summary_info,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.item = items[position]
    }

    override fun getItemCount() = items.size

    fun update(items: List<RepositorySummaryInfo>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val binding: ItemRepositorySummaryInfoBinding =
            requireNotNull(DataBindingUtil.bind(itemView))
    }
}

