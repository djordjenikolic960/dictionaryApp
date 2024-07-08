package com.example.dictionaryapp.features.history.presentation.domain.use_cases

import com.example.dictionaryapp.core.util.Resource
import com.example.dictionaryapp.features.feature_dictionary.domain.model.WordInfo
import com.example.dictionaryapp.features.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow

class GetWordInfoHistory(
    private val repository: WordInfoRepository
) {

    operator fun invoke(): Flow<Resource<List<WordInfo>>> {
        return repository.getWordInfoHistory()
    }
}