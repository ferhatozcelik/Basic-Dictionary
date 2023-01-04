package com.ferhatozcelik.basicdictionary.ui.fragments.searchdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ferhatozcelik.basicdictionary.R
import com.ferhatozcelik.basicdictionary.data.model.*
import com.ferhatozcelik.basicdictionary.databinding.FragmentSearchDetailBinding
import com.ferhatozcelik.basicdictionary.ui.adapters.SearchDetailAdapter
import com.ferhatozcelik.basicdictionary.ui.adapters.WordSynonymAdapter
import com.ferhatozcelik.basicdictionary.util.ProgressDialog
import com.ferhatozcelik.basicdictionary.util.StringExtension.Companion.capitalize
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchDetailFragment : Fragment(R.layout.fragment_search_detail) {

    private val viewModel: SearchDetailViewModel by viewModels()
    private lateinit var binding: FragmentSearchDetailBinding
    private lateinit var progressDialog: ProgressDialog

    private var searchWord: String? = null
    private var nounFilter = false
    private var verbFilter = false
    private var adjectiveFilter = false
    private var activiteButtonFlag = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchDetailBinding.inflate(inflater, container, false)
        progressDialog = activity?.let { ProgressDialog(it) }!!

        searchWord = arguments?.getString("searchWord")

        viewModel.wordTranslateResult(searchWord.toString())

        binding.apply {
            filterClearButton.visibility = View.GONE

            backImagebutton.setOnClickListener {
                activity?.findNavController(R.id.nav_host_fragment)?.popBackStack()
            }

            nounButton.setOnClickListener {
                nounFilter = toggleButton(nounFilter, "noun", binding.nounButton)
                refreshList()
            }

            verbButton.setOnClickListener {
                verbFilter = toggleButton(verbFilter, "verb", binding.verbButton)
                refreshList()
            }

            adjButton.setOnClickListener {
                adjectiveFilter = toggleButton(adjectiveFilter, "adjective", binding.adjButton)
                refreshList()
            }
            filterClearButton.setOnClickListener {
                viewModel.filterList.clear()
                refreshList()
            }
        }

        viewModel.searchData.observe(viewLifecycleOwner) {
            when (it) {
                is APIResponse.Success<*> -> {
                    val resultSuccess = it.result as WordDetail
                    binding.apply {
                        wordTextview.text = resultSuccess.word?.capitalize()
                        phoneticTextview.text = resultSuccess.phonetic
                    }

                    if (resultSuccess.wordDetailList.isNotEmpty()) {
                        FilterButtonActive(it.result.wordDetailList)
                        val adapter = SearchDetailAdapter(it.result.wordDetailList)
                        binding.meaningsRecyclerView.adapter = adapter
                    }

                    if (resultSuccess.wordSynonymList.isNotEmpty()) {
                        val adapter = WordSynonymAdapter(it.result.wordSynonymList)
                        binding.synonymRecyclerView.adapter = adapter
                    }

                    progressDialog.cancelDelayDialog()
                }
                is APIResponse.Error<*> -> {
                    val resultError = it.result as ErrorResult
                    Toast.makeText(context,resultError.message , Toast.LENGTH_SHORT).show()
                    progressDialog.cancelDelayDialog()
                    activity?.findNavController(R.id.nav_host_fragment)?.popBackStack()
                }
                is APIResponse.Loading<*> -> {
                    progressDialog.createProgressDialog()
                    progressDialog.showDialog()
                }
            }
        }

        return binding.root
    }

    private fun FilterButtonActive(wordDetailList: List<WordDetailList>) {
        if (!activiteButtonFlag){
            binding.nounButton.visibility = View.GONE
            binding.verbButton.visibility = View.GONE
            binding.adjButton.visibility = View.GONE
            for (item in wordDetailList) {
                when(item.partOfSpeech){
                    "noun" ->{
                        binding.nounButton.visibility = View.VISIBLE
                    }
                    "verb" ->{
                        binding.verbButton.visibility = View.VISIBLE
                    }
                    "adjective" ->{
                        binding.adjButton.visibility = View.VISIBLE
                    }
                }
            }
            activiteButtonFlag = true
        }
    }

    private fun toggleButton(type: Boolean, filter: String, button: AppCompatButton): Boolean {
        return if (!type) {
            viewModel.filterList.add(filter)
            button.setBackgroundResource(R.drawable.button_focused_background)
            true
        } else {
            viewModel.filterList.remove(filter)
            button.setBackgroundResource(R.drawable.button_background)
            false
        }
    }

    private fun refreshList() {

        if (viewModel.filterList.isNotEmpty()) {
            binding.filterClearButton.visibility = View.VISIBLE
        } else {
            binding.filterClearButton.visibility = View.GONE
            nounFilter = false
            verbFilter = false
            adjectiveFilter = false
            binding.nounButton.setBackgroundResource(R.drawable.button_background)
            binding.verbButton.setBackgroundResource(R.drawable.button_background)
            binding.adjButton.setBackgroundResource(R.drawable.button_background)
        }

        viewModel.wordTranslateResult(searchWord.toString())
    }

}