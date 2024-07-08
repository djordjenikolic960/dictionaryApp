package com.example.dictionaryapp.features.dictionary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dictionaryapp.features.dictionary.data.local.Converters
import com.example.dictionaryapp.features.dictionary.data.local.WordInfoDao
import com.example.dictionaryapp.features.feature_dictionary.data.local.entity.WordInfoEntity

@Database(
    entities = [WordInfoEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class WordInfoDatabase: RoomDatabase() {
    abstract val dao: WordInfoDao

}