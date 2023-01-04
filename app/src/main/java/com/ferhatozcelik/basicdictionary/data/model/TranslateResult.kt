package com.ferhatozcelik.basicdictionary.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TranslateResult(
    val word: String? = null,
    val phonetic: String? = null,
    val phonetics: List<Phonetic>? = arrayListOf(),
    val meanings: List<Meaning>? = arrayListOf(),
    val license: License? = null,
    val sourceUrls: List<String>? = arrayListOf()
) : Parcelable

@Parcelize
data class License(
    val name: String? = null,
    val url: String? = null
) : Parcelable

@Parcelize
data class Meaning(
    val partOfSpeech: String? = null,
    val definitions: List<Definition>? = arrayListOf(),
    val synonyms: List<String>? = arrayListOf(),
    val antonyms: List<String>? = arrayListOf()
) : Parcelable

@Parcelize
data class Definition(
    val definition: String,
    val synonyms: List<String>? = arrayListOf(),
    val antonyms: List<String>? = arrayListOf(),
    val example: String? = null
) : Parcelable

@Parcelize
data class Phonetic(
    val text: String? = null,
    val audio: String? = null,
    val sourceUrl: String? = null,
    val license: License? = null,
) : Parcelable

@Parcelize
data class Synonym(
    val word: String? = null,
    val score: Int? = null,
) : Parcelable