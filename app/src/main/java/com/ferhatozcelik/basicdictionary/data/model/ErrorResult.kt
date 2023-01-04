package com.ferhatozcelik.basicdictionary.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorResult(
    val title: String? = null,
    val message: String? = null,
    val resolution: String? = null
) : Parcelable

