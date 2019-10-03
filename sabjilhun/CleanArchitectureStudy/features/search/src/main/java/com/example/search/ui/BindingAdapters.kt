package com.example.search.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domains.entities.RepositorySummaryInfo
import com.example.search.ui.adapter.RepositorySummaryInfoAdapter


@BindingAdapter("items")
fun RecyclerView.setRepositorySummaryInfoList(
    items: List<RepositorySummaryInfo>?
) {
    if (items != null) {
        (adapter as? RepositorySummaryInfoAdapter)?.update(items)
    }
}