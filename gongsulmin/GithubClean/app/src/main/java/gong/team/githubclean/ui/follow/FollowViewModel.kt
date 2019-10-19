package gong.team.githubclean.ui.follow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gong.team.domain.entity.GithubFollowEntity
import gong.team.domain.usecase.GetGithubFollowerUseCase
import gong.team.githubclean.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class FollowViewModel(
    private val getGithubFollowerUseCase: GetGithubFollowerUseCase ,
    private val isFollowing: Boolean ,
    private val name: String
):BaseViewModel(){

    private val _followInfo = MutableLiveData<List<GithubFollowEntity>>()
    val followInfo: LiveData<List<GithubFollowEntity>>
        get() = _followInfo

    init {
        getGithubFollowerUseCase.invoke(name , isFollowing)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess =  {
                    _followInfo.value = it
                } ,
                onError =  {

                }
            ).addTo(compositeDisposable)
    }
}