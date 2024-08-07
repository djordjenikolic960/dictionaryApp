package com.example.dictionaryapp.features.feature_dictionary.domain.model

data class WordInfo(
    val meanings: List<Meaning>,
    val phonetic: String,
    val source: String,
    val word: String
)
