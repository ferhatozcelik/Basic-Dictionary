package com.ferhatozcelik.basicdictionary.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GeneralResult(
    val errorResult: ErrorResult? = null,
    val successResult: TranslateResult? = null,
) : Parcelable

@Parcelize
data class ErrorResult(
    val title: String? = null,
    val message: String? = null,
    val resolution: String? = null
) : Parcelable

