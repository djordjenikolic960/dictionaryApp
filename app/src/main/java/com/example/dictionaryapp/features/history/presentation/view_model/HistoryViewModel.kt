package com.example.dictionaryapp.features.history.presentation.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.dictionaryapp.features.history.presentation.domain.use_cases.GetWordInfoHistory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.example.dictionaryapp.core.util.Resource
import com.example.dictionaryapp.features.history.presentation.presentation.view_model.HistoryState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getWordInfoHistory: GetWordInfoHistory
) : ViewModel() {

    private val _state = mutableStateOf(HistoryState())
    val state: State<HistoryState> = _state

    init {
        get()
    }

    fun get() {
        viewModelScope.launch {
            getWordInfoHistory.invoke().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = false,
                            isError = false,
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            wordInfoItems = result.data ?: emptyList(),
                            isLoading = true,
                            isError = false,
                        )
                    }

                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            isError = true,
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}