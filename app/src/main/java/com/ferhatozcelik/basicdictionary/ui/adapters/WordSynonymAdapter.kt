package com.ferhatozcelik.basicdictionary.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ferhatozcelik.basicdictionary.data.entity.Search
import com.ferhatozcelik.basicdictionary.data.model.Synonym
import com.ferhatozcelik.basicdictionary.data.model.TranslateResult
import com.ferhatozcelik.basicdictionary.data.model.WordDetail
import com.ferhatozcelik.basicdictionary.data.model.WordDetailList
import com.ferhatozcelik.basicdictionary.databinding.MeaningsItemBinding
import com.ferhatozcelik.basicdictionary.databinding.SearchItemBinding
import com.ferhatozcelik.basicdictionary.databinding.SynonymItemBinding
import com.ferhatozcelik.basicdictionary.interfaces.ItemClickListener
import com.ferhatozcelik.basicdictionary.util.StringExtension.Companion.capitalize

class WordSynonymAdapter(
    var wordSynonymList: List<Synonym>
) : RecyclerView.Adapter<WordSynonymAdapter.SearchViewHolder>() {
    lateinit var itemSynonymItemBinding: SynonymItemBinding

     class SearchViewHolder(binding: SynonymItemBinding): RecyclerView.ViewHolder(binding.root) {
        var binding: SynonymItemBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = SynonymItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val wordSynonymItem = wordSynonymList[position]

        itemSynonymItemBinding = holder.binding

        holder.binding.apply {
            wordTextview.text = wordSynonymItem.word.toString()
        }
    }

    override fun getItemCount(): Int {
        return wordSynonymList.size
    }

}

