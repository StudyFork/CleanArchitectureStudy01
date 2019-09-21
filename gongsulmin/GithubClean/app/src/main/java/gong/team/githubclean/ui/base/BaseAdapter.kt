package gong.team.githubclean.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(private val itemId: Int)
    : RecyclerView.Adapter<BaseViewHolder<T>>() {

    val items = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val itemBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context) ,
            viewType ,
            parent ,
            false
        )

        return BaseViewHolder(itemBinding , itemId)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(items[position])
    }

    fun loadData(list: List<T>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
}