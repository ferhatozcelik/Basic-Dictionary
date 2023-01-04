package com.ferhatozcelik.basicdictionary.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ferhatozcelik.basicdictionary.data.local.Converters
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "search_table")
data class Search(
    @PrimaryKey(autoGenerate = true)
    var searchId: Int? = null,
    val searchWord: String?,
    @TypeConverters(Converters::class)
    val searchWordCreateAt: Long) : Parcelable