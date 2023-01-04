package com.ferhatozcelik.basicdictionary.repository

import com.ferhatozcelik.basicdictionary.data.entity.Search
import com.ferhatozcelik.basicdictionary.data.local.SearchDao
import com.ferhatozcelik.basicdictionary.data.model.Synonym
import com.ferhatozcelik.basicdictionary.data.model.TranslateResult
import com.ferhatozcelik.basicdictionary.data.remote.AppApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(
    private val appApi: AppApi,
    private val searchDao: SearchDao
) {

    fun getLastAllSearch() = searchDao.getSearch()

    suspend fun lastWordInsert(search: Search) = searchDao.insertOrUpdate(search)

    suspend fun  translateWord(word: String): Response<List<TranslateResult>> {
        return appApi.queryWord(word)
    }

    suspend fun synonymWord(word: String): Response<List<Synonym>> {
        return appApi.relSyn(word)
    }
}