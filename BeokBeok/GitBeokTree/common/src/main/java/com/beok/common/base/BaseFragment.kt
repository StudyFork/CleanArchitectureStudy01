package com.beok.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beok.navigation.NavigationCommand
import com.google.android.material.snackbar.Snackbar
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo

abstract class BaseFragment<VDB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes
    private val layoutId: Int
) : Fragment() {

    protected lateinit var binding: VDB private set
    protected abstract val viewModel: VM
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            layoutId,
            container,
            false
        )
        binding.lifecycleOwner = this
        observeNavigation()
        return binding.root
    }

    override fun onDestroy() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        super.onDestroy()
    }

    protected abstract fun initBinding()

    protected fun addDisposable(disposable: Disposable) = disposable.addTo(compositeDisposable)

    protected fun showSnackBar(msg: String?) {
        val targetView = view ?: return

        Snackbar.make(
            targetView,
            msg ?: "",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun observeNavigation() {
        viewModel.navigation.observe(
            this,
            Observer {
                it.getContentIfNotHandled()?.let { command ->
                    if (command is NavigationCommand.To) {
                        findNavController().navigate(command.directions)
                    }
                }
            }
        )
    }
}