package com.example.travelai.ui.fragments.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.travelai.databinding.ItemHomeOtherCitiesBinding
import com.example.travelai.databinding.ItemSearchTopDestinationBinding
import com.example.travelai.domain.home.MoreCity

class MoreCityAdapter(private val nav: () -> Unit = {}) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_NORMAL = 1
    private val VIEW_TYPE_SPECIAL = 2

    private val selected = mutableListOf<MoreCity>()

    private val diffCallBack = object : DiffUtil.ItemCallback<MoreCity>() {
        override fun areItemsTheSame(oldItem: MoreCity, newItem: MoreCity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MoreCity, newItem: MoreCity): Boolean {
            return oldItem == newItem
        }
    }

    private val diffUtil = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                val binding = ItemHomeOtherCitiesBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                NormalViewHolder(binding)
            }

            VIEW_TYPE_SPECIAL -> {
                val binding = ItemSearchTopDestinationBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SpecialViewHolder(binding)
            }

            else -> {
                val binding = ItemSearchTopDestinationBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SpecialViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NormalViewHolder -> holder.bind(diffUtil.currentList[position])
            is SpecialViewHolder -> holder.bind(diffUtil.currentList[position])
        }
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = diffUtil.currentList[position]
        return if (item.isSpecial) {
            VIEW_TYPE_SPECIAL
        } else {
            VIEW_TYPE_NORMAL
        }
    }

    inner class NormalViewHolder(private val binding: ItemHomeOtherCitiesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MoreCity) {
            binding.imgCity.setImageResource(item.img)
            binding.txtCity.text = item.name
        }
    }

    inner class SpecialViewHolder(private val binding: ItemSearchTopDestinationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MoreCity) {

            itemView.setOnClickListener {
                nav()
            }

            if (item.isBuildPlan) {
                binding.index.visibility = if (item.isSelected) View.VISIBLE else View.GONE
                binding.index.text = (selected.indexOf(item)+1).toString()
                itemView.setOnClickListener {
                    if (!item.isSelected) {
                        selected.add(item)
                    } else {
                        selected.remove(item)
                    }
                    item.isSelected = !item.isSelected
                    notifyItemChanged(layoutPosition)
                }
            }

            binding.imgCity.setImageResource(item.img)
            binding.txtCity.text = item.name
        }
    }

    fun submitList(items: List<MoreCity>) {
        diffUtil.submitList(items)
    }

    fun getSelected(): List<MoreCity> {
        return selected
    }

}