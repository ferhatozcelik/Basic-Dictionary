package com.ferhatozcelik.basicdictionary.ui.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ferhatozcelik.basicdictionary.R
import com.ferhatozcelik.basicdictionary.data.entity.Search
import com.ferhatozcelik.basicdictionary.data.model.APIResponse
import com.ferhatozcelik.basicdictionary.data.model.TranslateResult
import com.ferhatozcelik.basicdictionary.databinding.FragmentSearchBinding
import com.ferhatozcelik.basicdictionary.interfaces.ItemClickListener
import com.ferhatozcelik.basicdictionary.ui.adapters.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.apply {
            searchButton.setOnClickListener {
                if (searchEdittext.text.isNotEmpty()) {
                    goDetailView(searchEdittext.text.toString())
                }
            }
        }

        viewModel.getLastSearchData().observe(viewLifecycleOwner) {
            val adapter = SearchAdapter(it, searchItemClickListener)
            binding.lastSearchingList.adapter = adapter
        }

        return binding.root
    }

    private fun goDetailView(searchWord: String) {
        val bundle = Bundle()
        bundle.putString("searchWord", searchWord)
        activity?.findNavController(R.id.nav_host_fragment)
            ?.navigate(R.id.action_searchFragment_to_searchDetailFragment, bundle)
    }

    private val searchItemClickListener = object : ItemClickListener {
        override fun customItemListener(objects: Any) {
            val search: Search = objects as Search
            val bundle = Bundle()
            bundle.putString("searchWord", search.searchWord)
            activity?.findNavController(R.id.nav_host_fragment)
                ?.navigate(R.id.action_searchFragment_to_searchDetailFragment, bundle)
        }
    }

}