package com.example.dictionaryapp.features.history.presentation.presentation.view_model

import com.example.dictionaryapp.features.feature_dictionary.domain.model.WordInfo

data class HistoryState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)