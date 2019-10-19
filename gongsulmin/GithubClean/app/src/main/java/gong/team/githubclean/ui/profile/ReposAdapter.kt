package gong.team.githubclean.ui.profile

import gong.team.domain.entity.GithubUserRepoEntity
import gong.team.githubclean.BR
import gong.team.githubclean.R
import gong.team.githubclean.ui.base.BaseAdapter


class ReposAdapter : BaseAdapter<GithubUserRepoEntity>(BR.item) {
    override fun getItemViewType(position: Int): Int {
        return R.layout.item_repos
    }
}