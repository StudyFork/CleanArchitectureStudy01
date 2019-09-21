package dev.daeyeon.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.toast

abstract class BaseDialogFragment<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layout: Int
) : DialogFragment() {

    protected lateinit var binding: B
        private set

    protected abstract val viewModel: VM

    protected val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bind {
            lifecycleOwner = this@BaseDialogFragment.viewLifecycleOwner
        }
        observeEvent()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    protected fun bind(action: B.() -> Unit) {
        binding.run(action)
    }

    protected fun <T : ViewDataBinding> bind(binding: T, action: T.() -> Unit) {
        binding.run(action)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
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