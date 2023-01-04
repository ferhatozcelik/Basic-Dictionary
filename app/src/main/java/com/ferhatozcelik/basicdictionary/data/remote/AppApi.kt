package com.ferhatozcelik.basicdictionary.data.remote

import com.ferhatozcelik.basicdictionary.data.model.APIResponse
import com.ferhatozcelik.basicdictionary.data.model.Synonym
import com.ferhatozcelik.basicdictionary.data.model.TranslateResult
import retrofit2.Response
import retrofit2.http.*

interface AppApi {

    @GET("api/v2/entries/en/{word}")
    suspend fun  queryWord(@Path("word") word:String): Response<List<TranslateResult>>

    @GET("https://api.datamuse.com/words")
    suspend fun relSyn(@Query("rel_syn") rel_syn:String): Response<List<Synonym>>

}