package com.example.dictionaryapp.features.feature_dictionary.data.remote.dto

import com.example.dictionaryapp.features.feature_dictionary.domain.model.Phonetic

data class PhoneticDto(
    val audio: String,
    val license: LicenseDto,
    val sourceUrl: String,
    val text: String
) {
    fun toPhonetic(): Phonetic {
        return Phonetic(
            audio = audio,
            sourceUrl = sourceUrl,
            text = text
        )
    }
}