package com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.issues.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.kerollosragaie.githubviewer.core.utils.Constants.REPO_NAME_ARGUMENT_KEY
import com.kerollosragaie.githubviewer.core.utils.Constants.USER_NAME_ARGUMENT_KEY
import com.kerollosragaie.githubviewer.features.github_repositories.domain.models.ResponseIssueItemModel
import com.kerollosragaie.githubviewer.features.github_repositories.domain.usecase.GetRepoIssuesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IssuesViewModel @Inject constructor(
    private val getRepoIssuesUseCase: GetRepoIssuesUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _issuesState: MutableStateFlow<IssuesUIState> by lazy {
        MutableStateFlow(IssuesUIState.Loading)
    }
    val issuesState: StateFlow<IssuesUIState> by lazy { _issuesState }

    init {
        getIssuesList()
    }

    fun getIssuesList() {
        val ownerName = savedStateHandle.get<String>(USER_NAME_ARGUMENT_KEY)
        val repoName = savedStateHandle.get<String>(REPO_NAME_ARGUMENT_KEY)
        getIssuesList(ownerName, repoName)
    }

    private fun getIssuesList(
        ownerName: String?,
        repoName: String?,
    ) {
        viewModelScope.launch {
            if (ownerName != null && repoName != null) {
                val issuesList = getRepoIssuesUseCase(ownerName, repoName)
                if (issuesList.data != null) {
                    _issuesState.emit(IssuesUIState.Success(issuesList.data))
                } else {
                    _issuesState.emit(
                        IssuesUIState.Error(
                            issuesList.message ?: "Something went wrong!"
                        )
                    )
                }
            } else {
                _issuesState.emit(
                    IssuesUIState.Error(
                        "No params received, please try again later!"
                    )
                )
            }
        }
    }

}

sealed class IssuesUIState {
    object Loading : IssuesUIState()

    data class Success(
        val issuesList:
        Flow<PagingData<ResponseIssueItemModel>>
    ) : IssuesUIState()

    data class Error(val errorMessage: String) : IssuesUIState()
}