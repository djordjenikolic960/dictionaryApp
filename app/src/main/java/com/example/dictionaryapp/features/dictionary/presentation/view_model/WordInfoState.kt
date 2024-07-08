package com.example.dictionaryapp.features.feature_dictionary.presentation.view_model

import com.example.dictionaryapp.features.feature_dictionary.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)