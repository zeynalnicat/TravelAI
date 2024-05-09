package com.example.travelai.ui.fragments.search.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.travelai.databinding.ItemRecentlySearchedBinding

class RecentlySearchedAdapter:RecyclerView.Adapter<RecentlySearchedAdapter.ViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
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
        fun bind(item:String){
           binding.recentItem.text = item
        }
    }

    fun submitList(items:List<String>){
        diffUtil.submitList(items)
    }
}