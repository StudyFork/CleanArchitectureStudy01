package com.example.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.common.di.AppSubComponent
import com.example.common.di.SubComponentProvider
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class InjectActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var subComponent: AppSubComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        subComponent = (applicationContext as SubComponentProvider).getSubComponent()
        subComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    protected fun <VM : ViewModel> getViewModel(clazz: KClass<VM>) =
        ViewModelProvider(viewModelStore, viewModelFactory).get(clazz.java)
}