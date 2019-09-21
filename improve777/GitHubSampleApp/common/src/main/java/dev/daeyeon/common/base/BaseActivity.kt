package dev.daeyeon.common.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import org.jetbrains.anko.toast

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int,
    val bindingVariableId: Int? = null
) : AppCompatActivity() {

    protected lateinit var binding: B
        private set

    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        bindingVariableId?.let {
            binding.setVariable(bindingVariableId, viewModel)
        }
        binding.lifecycleOwner = this@BaseActivity
        observeEvent()
    }

    protected fun bind(action: B.() -> Unit) {
        binding.run(action)
    }

    protected fun <T : ViewDataBinding> bind(binding: T, action: T.() -> Unit) {
        binding.run(action)
    }

    protected open fun observeEvent() {
        viewModel.run {
            toastEvent.observe(this@BaseActivity, EventObserver {
                when (it) {
                    is String -> toast(it)
                    is Int -> toast(it)
                }
            })
        }
    }
}
