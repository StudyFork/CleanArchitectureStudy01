package gong.team.githubclean.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gong.team.domain.entity.GithubUserInfoEntity
import gong.team.domain.usecase.GetGithubUserInfoUseCase
import gong.team.githubclean.ui.Event
import gong.team.githubclean.ui.base.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class ProfileVIewModel(
    getGithubUserInfoUseCase: GetGithubUserInfoUseCase
): BaseViewModel() {

    private val _userInfo = MutableLiveData<GithubUserInfoEntity>()
    val userInfo: LiveData<GithubUserInfoEntity>
        get() = _userInfo

    private val _navigateToFollow = MutableLiveData<Event<Pair<Boolean , String>>>()
    val navigateToFollow: LiveData<Event<Pair<Boolean , String>>>
        get() = _navigateToFollow

    init {
        getGithubUserInfoUseCase.invoke()
            .subscribeBy(
                onSuccess = {
                    _userInfo.value = it
                }  ,
                onError =  {
                    it.printStackTrace()
                }
            ).addTo(compositeDisposable)
    }

    fun onClickFollow(isFollowing: Boolean , name: String) {
            _navigateToFollow.value = Event(Pair(isFollowing , name))
    }

}