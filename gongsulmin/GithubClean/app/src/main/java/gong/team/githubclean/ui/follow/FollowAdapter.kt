package gong.team.githubclean.ui.follow

import gong.team.domain.entity.GithubFollowEntity
import gong.team.githubclean.BR
import gong.team.githubclean.R
import gong.team.githubclean.ui.base.BaseAdapter


class FollowAdapter : BaseAdapter<GithubFollowEntity>(BR.follow) {
    override fun getItemViewType(position: Int): Int {
        return R.layout.item_follow
    }
}