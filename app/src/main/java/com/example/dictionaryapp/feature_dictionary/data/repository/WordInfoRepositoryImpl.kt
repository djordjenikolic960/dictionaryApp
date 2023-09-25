package com.example.dictionaryapp.feature_dictionary.data.repository

import com.example.dictionaryapp.core.util.Resource
import com.example.dictionaryapp.feature_dictionary.data.local.entity.WordInfoDao
import com.example.dictionaryapp.feature_dictionary.data.remote.DictionaryApi
import com.example.dictionaryapp.feature_dictionary.domain.model.WordInfo
import com.example.dictionaryapp.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordInfoRepository {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        //check if some words are in db
        val wordInfoes = dao.getWordInfoes(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfoes))

        //make api call and replace new words in db
        try {
            val remoteWordInfos = api.getWordInfo(word)
            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
        } catch (e: retrofit2.HttpException) {
           emit(Resource.Error(
               message = "Oops, something went wrong!",
               data = wordInfoes
           ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, check you internet connection.",
                data = wordInfoes
            ))
        }

        //if success emit success with new words
        val newWordInfos = dao.getWordInfoes(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))

    }
}