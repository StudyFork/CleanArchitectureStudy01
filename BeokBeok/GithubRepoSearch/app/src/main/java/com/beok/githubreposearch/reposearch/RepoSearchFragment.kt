package com.beok.githubreposearch.reposearch


import android.os.Bundle
import androidx.lifecycle.Observer
import com.beok.githubreposearch.BR
import com.beok.githubreposearch.R
import com.beok.githubreposearch.base.BaseFragment
import com.beok.githubreposearch.base.BaseRecyclerView
import com.beok.githubreposearch.custom.TextWatcher
import com.beok.githubreposearch.data.model.Repos
import com.beok.githubreposearch.databinding.FragmentRepoSearchBinding
import com.beok.githubreposearch.databinding.RvRepoItemBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class RepoSearchFragment : BaseFragment<FragmentRepoSearchBinding, RepoSearchViewModel>(
    R.layout.fragment_repo_search
) {

    override val viewModel by viewModel<RepoSearchViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initBinding()
        initRecyclerView()
        setTextChangedListener()
        setObserve()
    }

    private fun initBinding() {
        binding.vm = viewModel
        binding.executePendingBindings()
    }

    private fun initRecyclerView() {
        binding.rvContents.run {
            setHasFixedSize(true)
            adapter = object : BaseRecyclerView.BaseAdapter<List<Repos>, RvRepoItemBinding>(
                R.layout.rv_repo_item,
                BR.repos
            ) {}
        }
    }

    private fun setTextChangedListener() {
        addDisposable(
            Observable.create<CharSequence> { emitter ->
                binding.etSearch.addTextChangedListener(object : TextWatcher() {
                    override fun onTextChanged(
                        char: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        super.onTextChanged(
                            char,
                            start,
                            before,
                            count
                        )
                        if (char != null) {
                            emitter.onNext(char)
                        }

                    }
                })
            }.debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    binding.vm?.searchUserRepo(it.toString())
                }, {
                })
        )
    }

    private fun setObserve() {
        binding.vm?.errMsg?.observe(
            this,
            Observer {
                showSnackBar(it.message)
            }
        )
    }
}

