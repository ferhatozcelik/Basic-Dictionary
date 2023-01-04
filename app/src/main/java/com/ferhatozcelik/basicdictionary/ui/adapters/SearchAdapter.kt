package com.ferhatozcelik.basicdictionary.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ferhatozcelik.basicdictionary.data.entity.Search
import com.ferhatozcelik.basicdictionary.databinding.SearchItemBinding
import com.ferhatozcelik.basicdictionary.interfaces.ItemClickListener

class SearchAdapter(
    var goalsList: List<Search>,
    var itemClickListener: ItemClickListener
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    lateinit var itemGoalsPreviewBinding: SearchItemBinding

     class SearchViewHolder(binding: SearchItemBinding): RecyclerView.ViewHolder(binding.root) {
        var binding: SearchItemBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val translateItem = goalsList[position]

        itemGoalsPreviewBinding = holder.binding

        holder.binding.apply {
            searchTextview.text = translateItem.searchWord
        }

        holder.itemView.setOnClickListener {
            itemClickListener.customItemListener(translateItem)
        }

    }

    override fun getItemCount(): Int {
        return goalsList.size
    }

}

