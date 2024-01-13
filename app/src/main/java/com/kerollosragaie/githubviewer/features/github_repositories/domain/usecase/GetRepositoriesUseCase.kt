package com.kerollosragaie.githubviewer.features.github_repositories.domain.usecase

import androidx.paging.PagingData
import com.kerollosragaie.githubviewer.core.utils.ResultState
import com.kerollosragaie.githubviewer.features.github_repositories.domain.models.ResponseRepositoriesItemModel
import com.kerollosragaie.githubviewer.features.github_repositories.domain.repo.GitHubRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetRepositoriesUseCase(private val gitHubRepo: GitHubRepo) {
    suspend operator fun invoke(): ResultState<Flow<PagingData<ResponseRepositoriesItemModel>>> =
        withContext(Dispatchers.IO) {
            gitHubRepo.getRepositories()
        }
}