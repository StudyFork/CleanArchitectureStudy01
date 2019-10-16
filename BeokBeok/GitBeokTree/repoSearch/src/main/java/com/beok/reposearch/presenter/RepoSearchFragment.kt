package com.beok.reposearch.presenter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import com.beok.common.base.BaseFragment
import com.beok.reposearch.BR
import com.beok.reposearch.R
import com.beok.reposearch.databinding.FragmentRepoSearchBinding
import com.beok.reposearch.databinding.RvRepoItemBinding
import com.beok.reposearch.domain.entity.ReposEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class RepoSearchFragment : BaseFragment<FragmentRepoSearchBinding, RepoSearchViewModel>(
    R.layout.fragment_repo_search
) {

    override val viewModel: RepoSearchViewModel by viewModel()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initBinding()
        initRecyclerView()
        setTextChangedListener()
        setObserve()
    }

    override fun initBinding() {
        binding.vm = viewModel
    }

    private fun initRecyclerView() {
        binding.rvContents.run {
            setHasFixedSize(true)
            adapter =
                RepoSearchAdapter<List<ReposEntity>, RvRepoItemBinding>(
                    R.layout.rv_repo_item,
                    BR.repo,
                    viewModel
                )
        }
    }

    private fun setTextChangedListener() {
        addDisposable(
            Observable.create<CharSequence> { emitter ->
                binding.etSearch.addTextChangedListener(object : TextWatcher {
                    override fun onTextChanged(
                        char: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        if (char != null) emitter.onNext(char)
                    }

                    override fun afterTextChanged(s: Editable?) {
                        // NO OP
                    }

                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                        // NO OP
                    }
                })
            }.debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        binding.vm?.searchUserRepo(it.toString())
                    },
                    onError = {
                        showSnackBar(it.message)
                    }
                )
        )
    }

    private fun setObserve() {
        binding.vm?.errMsg?.observe(
            viewLifecycleOwner,
            Observer {
                showSnackBar(it.message)
            }
        )
    }
}