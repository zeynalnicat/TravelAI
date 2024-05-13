package com.example.travelai.ui.fragments.search.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.travelai.databinding.ItemRecentlySearchedBinding
import com.example.travelai.domain.search.SearchItem

class RecentlySearchedAdapter(private val nav: ()->Unit = {}):RecyclerView.Adapter<RecentlySearchedAdapter.ViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<SearchItem>() {
        override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem == newItem
        }
    }

    private val diffUtil = AsyncListDiffer(this, diffCallBack)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRecentlySearchedBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(diffUtil.currentList[position])
    }

    inner class ViewHolder(private val binding:ItemRecentlySearchedBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:SearchItem){
           binding.recentItem.text = item.title

            if(item.isTime){
                binding.recentItem.setOnClickListener {
                    nav()
                }
            }

        }
    }

    fun submitList(items:List<SearchItem>){
        diffUtil.submitList(items)
    }
}