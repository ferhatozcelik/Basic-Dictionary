package com.ferhatozcelik.basicdictionary.ui.fragments.search

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferhatozcelik.basicdictionary.data.model.APIResponse
import com.ferhatozcelik.basicdictionary.data.model.TranslateResult
import com.ferhatozcelik.basicdictionary.repository.SearchRepository
import com.ferhatozcelik.basicdictionary.util.NetworkUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    fun getLastSearchData() = searchRepository.getLastAllSearch()

}