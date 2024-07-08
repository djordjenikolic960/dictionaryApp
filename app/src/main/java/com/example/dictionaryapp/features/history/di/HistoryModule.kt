package com.example.dictionaryapp.features.history.di

import com.example.dictionaryapp.features.feature_dictionary.domain.repository.WordInfoRepository
import com.example.dictionaryapp.features.history.presentation.domain.use_cases.GetWordInfoHistory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HistoryModule {

    @Provides
    fun provideGetWordInfoHistory(repository: WordInfoRepository): GetWordInfoHistory {
        return GetWordInfoHistory(repository = repository)
    }
}