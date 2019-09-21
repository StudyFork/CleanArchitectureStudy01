package com.beok.githubreposearch.reposearch


import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.beok.githubreposearch.BR
import com.beok.githubreposearch.R
import com.beok.githubreposearch.base.BaseFragment
import com.beok.githubreposearch.base.BaseRecyclerView
import com.beok.githubreposearch.custom.TextWatcher
import com.beok.githubreposearch.data.RepoSearchRepository
import com.beok.githubreposearch.data.model.Repos
import com.beok.githubreposearch.data.remote.RepoSearchRemoteDataSource
import com.beok.githubreposearch.data.remote.RepoSearchRetrofit
import com.beok.githubreposearch.databinding.FragmentRepoSearchBinding
import com.beok.githubreposearch.databinding.RvRepoItemBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class RepoSearchFragment : BaseFragment<FragmentRepoSearchBinding, RepoSearchViewModel>(
    R.layout.fragment_repo_search
) {

    override val viewModel by lazy {
        ViewModelProviders.of(
            this,
            object : ViewModelProvider.Factory {

                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                    RepoSearchViewModel(
                        RepoSearchRepository(
                            RepoSearchRemoteDataSource(RepoSearchRetrofit.service)
                        )
                    ) as T
            }
        )[RepoSearchViewModel::class.java]
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initBinding()
        initRecyclerView()
        setTextChangedListener()
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
}

