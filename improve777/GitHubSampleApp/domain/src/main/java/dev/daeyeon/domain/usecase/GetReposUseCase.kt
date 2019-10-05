package dev.daeyeon.domain.usecase

import dev.daeyeon.domain.DataResult
import dev.daeyeon.domain.entity.Repo
import dev.daeyeon.domain.repository.RepoRepository
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

class GetReposUseCase(
    private val repoRepository: RepoRepository
) {
    operator fun invoke(
        userName: String,
        isForceUpdate: Boolean = true,
        onResult: (result: DataResult<List<Repo>>) -> Unit
    ): Disposable =
        repoRepository.getRepos(
            userName = userName,
            isForceUpdate = isForceUpdate
        )
            .doOnSubscribe { onResult(DataResult.loading()) }
            .subscribeBy(
                onError = {
                    onResult(DataResult.error(it))
                },
                onComplete = {},
                onNext = {
                    onResult(DataResult.success(it))
                }
            )
}