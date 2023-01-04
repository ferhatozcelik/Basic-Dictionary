package com.ferhatozcelik.basicdictionary.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WordDetail(
    val word: String? = null,
    val phonetic: String? = null,
    val wordDetailList: List<WordDetailList> = arrayListOf(),
    val wordSynonymList: List<Synonym> = arrayListOf()
) : Parcelable


@Parcelize
data class WordDetailList(
    val partOfSpeechNumber: Int? = null,
    val partOfSpeech: String? = null,
    val definitionText: String? = null,
    val exampleText: String? = null
) : Parcelable