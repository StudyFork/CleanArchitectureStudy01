package gong.team.githubclean.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gong.team.domain.entity.GithubUserEntity
import gong.team.domain.usecase.GetGithubUserInfoUseCase
import gong.team.githubclean.ui.base.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class ProfileVIewModel(
    getGithubUserInfoUseCase: GetGithubUserInfoUseCase
): BaseViewModel() {

    private val _userInfo = MutableLiveData<GithubUserEntity>()
    val userInfo: LiveData<GithubUserEntity>
    get() = _userInfo

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

}