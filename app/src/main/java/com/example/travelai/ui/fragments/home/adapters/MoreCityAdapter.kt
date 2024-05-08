package com.example.travelai.ui.fragments.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.travelai.databinding.ItemHomeOtherCitiesBinding
import com.example.travelai.domain.home.DiscoverCity
import com.example.travelai.domain.home.MoreCity

class MoreCityAdapter : RecyclerView.Adapter<MoreCityAdapter.ViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<MoreCity>() {
        override fun areItemsTheSame(oldItem: MoreCity, newItem: MoreCity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MoreCity, newItem: MoreCity): Boolean {
            return oldItem == newItem
        }
    }

    private val diffUtil = AsyncListDiffer(this, diffCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemHomeOtherCitiesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(diffUtil.currentList[position])
    }

    inner class ViewHolder(private val binding: ItemHomeOtherCitiesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MoreCity) {
            binding.imgCity.setImageResource(item.img)
            binding.txtCity.text = item.name
        }
    }


    fun submitList(items: List<MoreCity>) {
        diffUtil.submitList(items)
    }
}