package com.ferhatozcelik.basicdictionary.ui.fragments.search

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ferhatozcelik.basicdictionary.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    fun getLastSearchData() = searchRepository.getLastAllSearch()

}