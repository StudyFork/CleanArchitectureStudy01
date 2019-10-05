package dev.daeyeon.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.daeyeon.common.exception.NetworkException
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import java.net.UnknownHostException

abstract class BaseViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    protected val _toastEvent = MutableLiveData<Event<Any>>()
    val toastEvent: LiveData<Event<Any>> = _toastEvent

    /**
     * 프로그레스바
     */
    protected val _isShowProgressBar = MutableLiveData<Boolean>()
    val isShowProgressBar: LiveData<Boolean> = _isShowProgressBar

    /**
     * 데이터가 없을 때
     */
    protected val _isEmptyData = MutableLiveData<Boolean>()
    val isEmptyData: LiveData<Boolean> = _isEmptyData

    /**
     * 네트워크 연결
     */
    protected val _hasNotConnectedNetwork = MutableLiveData<Boolean>()
    val hasNotConnectedNetwork: LiveData<Boolean> = _hasNotConnectedNetwork

    init {
        _isShowProgressBar.value = false
        _isEmptyData.value = false
        _hasNotConnectedNetwork.value = false
    }

    fun sendToast(msg: Any?) {
        msg?.let {
            _toastEvent.value = Event(msg)
        }
    }

    open fun refreshView() {}

    /**
     * 공통 예외 처리
     */
    protected fun handleException(t: Throwable, block: ((t: Throwable) -> Unit)? = null) {
        Timber.w(t)

        when (t) {
            is NetworkException, is UnknownHostException -> {
                showNetworkError()
            }
            else -> {
                hideNetworkError()
            }
        }
        block?.invoke(t)
    }

    protected fun showErrorMsg(msg: String?) {
        if ((msg ?: "").isNotEmpty()) {
            sendToast(msg)
        }
    }

    protected fun hideNetworkError() {
        _hasNotConnectedNetwork.value = false
    }

    protected fun showNetworkError() {
        _hasNotConnectedNetwork.value = true
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}