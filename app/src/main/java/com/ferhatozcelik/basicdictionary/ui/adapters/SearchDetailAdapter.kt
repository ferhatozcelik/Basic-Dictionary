package com.ferhatozcelik.basicdictionary.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ferhatozcelik.basicdictionary.data.model.WordDetailList
import com.ferhatozcelik.basicdictionary.databinding.MeaningsItemBinding
import com.ferhatozcelik.basicdictionary.util.StringExtension.Companion.capitalize

class SearchDetailAdapter(
    var wordDetailList: List<WordDetailList>
) : RecyclerView.Adapter<SearchDetailAdapter.SearchViewHolder>() {
    lateinit var itemGoalsPreviewBinding: MeaningsItemBinding

    class SearchViewHolder(binding: MeaningsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: MeaningsItemBinding

        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding =
            MeaningsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val wordDetailItem = wordDetailList[position]

        itemGoalsPreviewBinding = holder.binding

        holder.binding.apply {
            numberTextView.text = wordDetailItem.partOfSpeechNumber.toString() + " - "
            partOfSpeechTextView.text = wordDetailItem.partOfSpeech.toString().capitalize()
            definitionTextView.text = wordDetailItem.definitionText.toString()

            if (wordDetailItem.exampleText == null){
                exampleTitleTextview.visibility = View.GONE
                exampleTextview.visibility = View.GONE
            }else{
                exampleTitleTextview.visibility = View.VISIBLE
                exampleTextview.visibility = View.VISIBLE
                exampleTextview.text = wordDetailItem.exampleText.toString()
            }
        }
    }

    override fun getItemCount(): Int {
        return wordDetailList.size
    }

}

