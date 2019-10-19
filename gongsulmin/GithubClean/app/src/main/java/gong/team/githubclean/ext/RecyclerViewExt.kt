package gong.team.githubclean.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import gong.team.domain.entity.GithubFollowEntity
import gong.team.domain.entity.GithubSearchResultModel
import gong.team.domain.entity.GithubUserRepoEntity
import gong.team.githubclean.adapter.MainAdapter
import gong.team.githubclean.ui.follow.FollowAdapter
import gong.team.githubclean.ui.profile.ReposAdapter


@BindingAdapter("setItems")
fun <T> RecyclerView.setItems(items: T) {

    if (items != null){
        when (adapter) {
            is MainAdapter -> (adapter as MainAdapter).loadData(items as List<GithubSearchResultModel>)
            is ReposAdapter -> {
                (adapter as ReposAdapter).loadData(items as List<GithubUserRepoEntity>)
            }
            is FollowAdapter -> {
                (adapter as FollowAdapter).loadData(items as List<GithubFollowEntity>)
            }
            else -> {
            }
        }
    }

}