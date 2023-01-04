package com.ferhatozcelik.basicdictionary.ui.fragments.searchdetail

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferhatozcelik.basicdictionary.data.entity.Search
import com.ferhatozcelik.basicdictionary.data.model.*
import com.ferhatozcelik.basicdictionary.repository.SearchRepository
import com.ferhatozcelik.basicdictionary.util.NetworkUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class SearchDetailViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val searchData: MutableLiveData<APIResponse<Any>> = MutableLiveData()
    val filterList = mutableListOf<String>()

    fun wordTranslateResult(word: String) = viewModelScope.launch {
        safeTranslateData(word)
    }

    private suspend fun safeTranslateData(word: String) {
        searchData.postValue(APIResponse.Loading())
        try {
            if (NetworkUtil.hasInternetConnection(context)) {
                val response = searchRepository.translateWord(word)
                val result = handleTranslateResponse(response)

                val responseSynonym = searchRepository.synonymWord(word)
                val resultSynonym = handleSynonymResponse(responseSynonym)

                val listWordDetail = mutableListOf<WordDetailList>()
                val listWordSynonym = mutableListOf<Synonym>()

                var wordVerifyed: String = word
                var phoneticVerifyed: String = word

                var nounNumber = 1
                var verbNumber = 1
                var verbAdjective = 1

                listWordDetail.clear()
                for (detailItem in result) {
                    if (detailItem.phonetic != null && detailItem.word != null && detailItem.word == word) {
                        wordVerifyed = detailItem.word
                        phoneticVerifyed = detailItem.phonetic

                        if (detailItem.meanings != null) {
                            if (filterList.isNotEmpty()) {
                                for (item in detailItem.meanings) {
                                    if (item.partOfSpeech in filterList) {
                                        if (item.partOfSpeech == "noun") {
                                            for (definitionItem in item.definitions!!) {
                                                listWordDetail.add(
                                                    WordDetailList(
                                                        nounNumber,
                                                        item.partOfSpeech,
                                                        definitionItem.definition,
                                                        definitionItem.example
                                                    )
                                                )
                                                nounNumber++
                                            }
                                        }
                                        if (item.partOfSpeech == "verb") {
                                            for (definitionItem in item.definitions!!) {
                                                listWordDetail.add(
                                                    WordDetailList(
                                                        verbNumber,
                                                        item.partOfSpeech,
                                                        definitionItem.definition,
                                                        definitionItem.example
                                                    )
                                                )
                                                verbNumber++
                                            }
                                        }
                                        if (item.partOfSpeech == "adjective") {
                                            for (definitionItem in item.definitions!!) {
                                                listWordDetail.add(
                                                    WordDetailList(
                                                        verbAdjective,
                                                        item.partOfSpeech,
                                                        definitionItem.definition,
                                                        definitionItem.example
                                                    )
                                                )
                                                verbAdjective++
                                            }
                                        }
                                    }
                                }
                            } else {
                                for (item in detailItem.meanings) {
                                    if (item.partOfSpeech == "noun") {
                                        for (definitionItem in item.definitions!!) {
                                            listWordDetail.add(
                                                WordDetailList(
                                                    nounNumber,
                                                    item.partOfSpeech,
                                                    definitionItem.definition,
                                                    definitionItem.example
                                                )
                                            )
                                            nounNumber++
                                        }
                                    }
                                    if (item.partOfSpeech == "verb") {
                                        for (definitionItem in item.definitions!!) {
                                            listWordDetail.add(
                                                WordDetailList(
                                                    verbNumber,
                                                    item.partOfSpeech,
                                                    definitionItem.definition,
                                                    definitionItem.example
                                                )
                                            )
                                            verbNumber++
                                        }
                                    }
                                    if (item.partOfSpeech == "adjective") {
                                        for (definitionItem in item.definitions!!) {
                                            listWordDetail.add(
                                                WordDetailList(
                                                    verbAdjective,
                                                    item.partOfSpeech,
                                                    definitionItem.definition,
                                                    definitionItem.example
                                                )
                                            )
                                            verbAdjective++
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                listWordSynonym.sortBy { it.score }
                for (i in 0..4 ){
                    listWordSynonym.add(resultSynonym[i])
                }

                searchData.postValue(
                    APIResponse.Success(
                        WordDetail(
                            wordVerifyed,
                            phoneticVerifyed,
                            listWordDetail,
                            listWordSynonym
                        )
                    )
                )
            } else {
                searchData.postValue(
                    APIResponse.Error(
                        ErrorResult(
                            "NETWORK_ERROR",
                            "No Internet Connection",
                            "Internet Connect"
                        )
                    )
                )
            }

        } catch (ex: Exception) {
            when (ex) {
                is IOException -> searchData.postValue(
                    APIResponse.Error(
                        ErrorResult(
                            "Network Failure",
                            ex.message.toString(),
                            "Internet Connect"
                        )
                    )
                )
                else -> searchData.postValue(
                    APIResponse.Error(
                        ErrorResult(
                            "Sorry pal, we couldn't find definitions for the word you were looking for.",
                            "No Definitions Found",
                            "You can try the search again at later time or head to the web instead."
                        )
                    )
                )
            }
        }
    }

    private suspend fun handleTranslateResponse(response: Response<List<TranslateResult>>): List<TranslateResult> {
        if (response.isSuccessful) {
            response.body()?.let {
                searchRepository.lastWordInsert(
                        Search(
                            searchWord = response.body()?.get(0)!!.word,
                            searchWordCreateAt = System.currentTimeMillis()
                        )
                    )
                return response.body()!!
            }
        }
        return mutableListOf()
    }

    private fun handleSynonymResponse(response: Response<List<Synonym>>): List<Synonym> {
        if (response.isSuccessful) {
            response.body()?.let {
                return response.body()!!
            }
        }
        return mutableListOf()
    }

}