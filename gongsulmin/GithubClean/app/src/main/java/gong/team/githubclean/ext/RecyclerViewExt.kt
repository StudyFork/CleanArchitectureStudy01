package gong.team.githubclean.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import gong.team.domain.entity.GithubSearchResultModel
import gong.team.githubclean.adapter.MainAdapter


@BindingAdapter("setItems")
fun <T> RecyclerView.setItems(items: T) {

    if (items != null){
        (adapter as MainAdapter).loadData(items as List<GithubSearchResultModel>)
    }

}