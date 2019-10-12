package gong.team.githubclean.ui.login

import android.util.Base64
import android.view.View
import androidx.lifecycle.MutableLiveData
import gong.team.domain.usecase.GetGithubUserTokenUseCase
import gong.team.githubclean.ui.base.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class LoginViewModel(
    private val getGithubUserTokenUseCase: GetGithubUserTokenUseCase
): BaseViewModel() {

    val loginId = MutableLiveData<String>("")
    val loginPassword = MutableLiveData<String>("")

    fun onClickGetToken(view: View) {
        getGithubUserTokenUseCase.invoke(getHeader(loginId.value!! , loginPassword.value!!))
            .subscribeBy(
                onSuccess = {
                } ,
                onError = {
                    it.printStackTrace()
                }
            ).addTo(compositeDisposable)
    }

    private fun getHeader(name: String , password: String): String {
        val headerString = "$name:$password"
        return "Basic "+Base64.encodeToString(headerString.toByteArray(),Base64.NO_WRAP)
    }

}