package com.kerollosragaie.githubviewer.features.github_repositories.presentation.screens.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.kerollosragaie.githubviewer.features.github_repositories.domain.models.ResponseRepositoriesItemModel
import com.kerollosragaie.githubviewer.features.github_repositories.domain.usecase.GetRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
) : ViewModel() {
    private val _mainState: MutableStateFlow<MainUIState> by lazy {
        MutableStateFlow(MainUIState.Loading)
    }
    val mainState: StateFlow<MainUIState> by lazy { _mainState }

    private val _searchQuery by lazy { MutableStateFlow("") }
    val searchQuery: StateFlow<String> by lazy { _searchQuery }

    init {
        getRepositoriesList()
    }

    fun getRepositoriesList() {
        viewModelScope.launch {
            val reposList = getRepositoriesUseCase.invoke()
            if (reposList.data != null) {
                _mainState.emit(MainUIState.Success(reposList.data))
            } else {
                _mainState.emit(
                    MainUIState.Error(
                        reposList.message ?: "Something went wrong!"
                    )
                )
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}

sealed class MainUIState {
    object Loading : MainUIState()

    data class Success(
        val reposList:
        Flow<PagingData<ResponseRepositoriesItemModel>>
    ) : MainUIState()

    data class Error(val errorMessage: String) : MainUIState()
}