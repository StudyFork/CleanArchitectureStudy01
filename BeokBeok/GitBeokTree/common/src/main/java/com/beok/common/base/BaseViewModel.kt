package com.beok.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.beok.navigation.NavigationCommand

abstract class BaseViewModel : ViewModel() {

    private val _navigation = MutableLiveData<NavigationCommand>()
    val navigation: LiveData<NavigationCommand> get() = _navigation

    protected fun navigate(directions: NavDirections) {
        _navigation.value = NavigationCommand.To(directions)
    }
}