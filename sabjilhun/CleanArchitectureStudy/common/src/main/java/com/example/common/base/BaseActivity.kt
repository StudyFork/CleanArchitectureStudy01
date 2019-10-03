package com.example.common.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.common.R
import com.example.common.databinding.BaseLayoutBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.reflect.KClass


abstract class BaseActivity<B : ViewDataBinding>(
    @LayoutRes private val layoutId: Int
) : InjectActivity() {

    protected lateinit var binding: B
        private set

    private lateinit var baseLayout: BaseLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this

        baseLayout = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.base_layout,
            binding.root as ViewGroup,
            false
        )

        (binding.root as ViewGroup).addView(baseLayout.root)
    }


    protected fun <VM : BaseViewModel> getViewModel(clazz: KClass<VM>): VM {
        val viewModel = ViewModelProvider(viewModelStore, viewModelFactory).get(clazz.java)
        observingBaseViewModel(viewModel)
        return viewModel
    }

    private fun observingBaseViewModel(baseViewModel: BaseViewModel) {
        baseViewModel.loading.observe(this, Observer {
            baseLayout.pb.visibility = if (it) View.VISIBLE else View.GONE
        })
        baseViewModel.showErrorMessage.observe(this, Observer {
            Snackbar.make(
                binding.root,
                getString(R.string.network_fail_message),
                Snackbar.LENGTH_SHORT
            ).show()
        })
    }
}

