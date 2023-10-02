package com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.repository_details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kerollosragaie.githubviewer.core.utils.Constants.REPO_NAME_ARGUMENT_KEY
import com.kerollosragaie.githubviewer.core.utils.Constants.USER_NAME_ARGUMENT_KEY
import com.kerollosragaie.githubviewer.features.github_repositories.domain.models.ResponseRepoDetailsModel
import com.kerollosragaie.githubviewer.features.github_repositories.domain.usecase.GetRepoDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    private val getRepoDetailsUseCase: GetRepoDetailsUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _repoDetails: MutableStateFlow<RepoDetailsUIState> by lazy {
        MutableStateFlow(
            RepoDetailsUIState.Loading
        )
    }

    val repoDetails: StateFlow<RepoDetailsUIState> by lazy { _repoDetails }

    init {
        getRepoDetails()
    }

    fun getRepoDetails() {
        val ownerName = savedStateHandle.get<String>(USER_NAME_ARGUMENT_KEY)
        val repoName = savedStateHandle.get<String>(REPO_NAME_ARGUMENT_KEY)
        getRepoDetails(ownerName, repoName)
    }

    private fun getRepoDetails(
        ownerName: String?,
        repoName: String?,
    ) {
        viewModelScope.launch {
            if (ownerName != null && repoName != null) {
                val responseRepoDetailsModel = getRepoDetailsUseCase.invoke(
                    ownerName = ownerName,
                    repoName = repoName,
                )
                if (responseRepoDetailsModel.data != null) {
                    _repoDetails.emit(RepoDetailsUIState.Success(responseRepoDetailsModel.data))
                } else {
                    _repoDetails.emit(
                        RepoDetailsUIState.Error(
                            errorMessage = responseRepoDetailsModel.message
                                ?: "Something went wrong!"
                        )
                    )
                }
            } else {
                _repoDetails.emit(RepoDetailsUIState.Error("No params received, please try again later!"))
            }
        }
    }

}

sealed class RepoDetailsUIState {
    object Loading : RepoDetailsUIState()
    data class Success(val repoDetails: ResponseRepoDetailsModel) : RepoDetailsUIState()
    data class Error(val errorMessage: String) : RepoDetailsUIState()
}