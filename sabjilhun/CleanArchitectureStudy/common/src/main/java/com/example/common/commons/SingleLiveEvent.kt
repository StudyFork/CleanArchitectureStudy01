package com.example.common.commons

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean


class SingleLiveEvent<T> : MutableLiveData<T> {

    constructor() : super()

    constructor(value: T) : super(value)

    private val pendingValue = AtomicBoolean(false)

    override fun setValue(value: T) {
        pendingValue.set(true)
        super.setValue(value)
    }

    override fun postValue(value: T) {
        pendingValue.set(true)
        super.setValue(value)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, Observer<T> { t ->
            if (pendingValue.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }
}
