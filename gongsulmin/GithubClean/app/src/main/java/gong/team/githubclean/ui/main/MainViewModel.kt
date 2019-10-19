package gong.team.githubclean.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gong.team.domain.entity.GithubSearchResultModel
import gong.team.domain.usecase.GetGithubSearchListUsecase
import gong.team.githubclean.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class MainViewModel(
    private val getGithubSearchListUsecase: GetGithubSearchListUsecase
): BaseViewModel(){

    private val _progressBarVisible = MutableLiveData<Boolean>(false)
    val progressBarVisible: LiveData<Boolean>
        get() = _progressBarVisible

    private val _searchResultList = MutableLiveData<List<GithubSearchResultModel>>()
    val searchResultList: LiveData<List<GithubSearchResultModel>>
        get() = _searchResultList

    val searchKeyword = BehaviorSubject.create<String>()

    init {
        searchKeyword
            .debounce(1000, TimeUnit.MILLISECONDS)
            .doOnNext {
                _progressBarVisible.postValue(true)
            }
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.io())
            .filter { it.isNotBlank() }
            .subscribeBy {
                getGithubSearchListUsecase
                    .invoke(it , "1 " , "10")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        {
                            _progressBarVisible.postValue(false)
                            it.printStackTrace()
                        },
                        {
                            _progressBarVisible.postValue(false)
                            _searchResultList.value = it
                        }
                    )
            }.addTo(compositeDisposable)
    }

}