package gong.team.githubclean.ui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder<T>(
    private val itemBinding: ViewDataBinding ,
    private val itemId: Int
): RecyclerView.ViewHolder(itemBinding.root){

    fun bind(item: T) {
        itemBinding.setVariable(itemId , item)
        itemBinding.executePendingBindings()
    }
}