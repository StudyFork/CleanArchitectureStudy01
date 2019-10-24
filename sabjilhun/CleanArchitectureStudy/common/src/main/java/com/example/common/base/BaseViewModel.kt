package com.example.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.common.commons.SingleLiveEvent
import com.example.common.ext.addTo
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


abstract class BaseViewModel : ViewModel() {

    protected val disposables = CompositeDisposable()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _showApiErrorMessage = SingleLiveEvent<Unit>()
    val showApiErrorMessage: LiveData<Unit> = _showApiErrorMessage

    override fun onCleared() {
        disposables.clear()
    }

    protected fun handleApiErrorMessage(throwable: Throwable) {
        throwable.printStackTrace()
        _showApiErrorMessage.setValue(Unit)
    }

    protected fun Disposable.add() {
        this.addTo(disposables)
    }

    protected fun <T> apiLoadingTransformer(): SingleTransformer<T, T> {
        return SingleTransformer {
            it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { _loading.value = true }
                .doFinally { _loading.value = false }
        }
    }
}
