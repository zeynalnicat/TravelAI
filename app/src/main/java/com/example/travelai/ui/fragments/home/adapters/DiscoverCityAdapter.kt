package com.example.travelai.ui.fragments.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.travelai.databinding.ItemHomeDiscoverBinding
import com.example.travelai.domain.home.DiscoverCity
import com.example.travelai.domain.home.SearchItem

class DiscoverCityAdapter : RecyclerView.Adapter<DiscoverCityAdapter.ViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<DiscoverCity>() {
        override fun areItemsTheSame(oldItem: DiscoverCity, newItem: DiscoverCity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DiscoverCity, newItem: DiscoverCity): Boolean {
            return oldItem == newItem
        }
    }

    private val diffUtil = AsyncListDiffer(this, diffCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemHomeDiscoverBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(diffUtil.currentList[position])
    }

    inner class ViewHolder(private val binding: ItemHomeDiscoverBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DiscoverCity) {
            binding.txtCategory.text = item.category
            binding.txtTitle.text = item.title
            binding.txtRating.text = item.rating.toString()
            binding.imgBackground.setImageResource(item.img)

        }
    }

    fun submitList(items: List<DiscoverCity>) {
        diffUtil.submitList(items)
    }
}