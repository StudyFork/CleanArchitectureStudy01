package com.example.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.common.Injector
import javax.inject.Inject

abstract class InjectActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var injector: Injector

    override fun onCreate(savedInstanceState: Bundle?) {
        injector = (applicationContext as Injector.Provider)
            .provide(this)
            .apply {
                inject(this@InjectActivity)
            }
        super.onCreate(savedInstanceState)
    }
}