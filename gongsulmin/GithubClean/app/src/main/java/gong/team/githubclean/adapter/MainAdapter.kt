package gong.team.githubclean.adapter

import gong.team.domain.entity.GithubSearchResultModel
import gong.team.githubclean.BR
import gong.team.githubclean.R
import gong.team.githubclean.ui.base.BaseAdapter

class MainAdapter: BaseAdapter<GithubSearchResultModel>(BR.item){
    override fun getItemViewType(position: Int): Int {
        return R.layout.item_search_result
    }
}
