package com.example.dictionaryapp.features.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dictionaryapp.features.feature_dictionary.domain.model.Meaning
import com.example.dictionaryapp.features.feature_dictionary.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    val word: String,
    val meanings: List<Meaning>,
    val phonetic: String,
    val source: String,
    @PrimaryKey val id: Int? = null
) {
    fun toWordInfo(): WordInfo {
        return WordInfo(
            word = word,
            meanings = meanings,
            phonetic = phonetic,
            source = source,
        )
    }
}
