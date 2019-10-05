package com.beok.common.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VDB : ViewDataBinding>(
    @LayoutRes
    private val layoutResID: Int
) : AppCompatActivity() {

    protected lateinit var binding: VDB private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.inflate(
            layoutInflater,
            layoutResID,
            null,
            false
        )
        setContentView(binding.root)
        binding.lifecycleOwner = this
    }
}
