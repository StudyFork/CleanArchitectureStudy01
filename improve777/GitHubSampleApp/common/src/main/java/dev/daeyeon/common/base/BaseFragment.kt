package dev.daeyeon.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import org.jetbrains.anko.toast

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int,
    private val bindingVariableId: Int? = null
) : Fragment() {

    protected lateinit var binding: B
        private set

    protected abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindingVariableId?.let {
            binding.setVariable(bindingVariableId, viewModel)
        }
        binding.lifecycleOwner = this@BaseFragment.viewLifecycleOwner
        observeEvent()
    }

    protected abstract fun initView()

    protected fun bind(action: B.() -> Unit) {
        binding.run(action)
    }

    protected fun <T : ViewDataBinding> bind(binding: T, action: T.() -> Unit) {
        binding.run(action)
    }

    protected open fun observeEvent() {
        viewModel.run {
            toastEvent.observe(viewLifecycleOwner, EventObserver {
                when (it) {
                    is String -> activity?.toast(it)
                    is Int -> activity?.toast(it)
                }
            })
        }
    }
}