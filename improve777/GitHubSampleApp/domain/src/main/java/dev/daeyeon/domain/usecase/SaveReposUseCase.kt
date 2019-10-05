package dev.daeyeon.domain.usecase

import dev.daeyeon.domain.DataResult
import dev.daeyeon.domain.entity.Repo
import dev.daeyeon.domain.repository.RepoRepository
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

class SaveReposUseCase(
    private val repoRepository: RepoRepository
) {
    operator fun invoke(
        repos: List<Repo>,
        onResult: (result: DataResult<Unit>) -> Unit
    ): Disposable =
        repoRepository.saveRepos(repos)
            .doOnSubscribe { onResult(DataResult.loading()) }
            .subscribeBy(
                onError = {
                    onResult(DataResult.error(it))
                },
                onComplete = {
                    onResult(DataResult.success(Unit))
                }
            )
}
