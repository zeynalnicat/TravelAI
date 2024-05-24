package com.example.travelai.ui.fragments.plans.buildplan.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.travelai.databinding.ItemWhosComingBinding
import com.example.travelai.domain.plans.WhosComing

class WhosComingAdapter(private val nav : ()->Unit , private val screenWidth : Int) : RecyclerView.Adapter<WhosComingAdapter.ViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<WhosComing>() {
        override fun areItemsTheSame(oldItem: WhosComing, newItem: WhosComing): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: WhosComing, newItem: WhosComing): Boolean {
            return oldItem == newItem
        }
    }

    private val diffUtil = AsyncListDiffer(this, diffCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemWhosComingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(diffUtil.currentList[position])
    }


    inner class ViewHolder(private val binding: ItemWhosComingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WhosComing) {
            val layoutParam = itemView.layoutParams
            layoutParam.width = (screenWidth * 0.42).toInt()
            itemView.layoutParams = layoutParam
            itemView.setOnClickListener {
                nav()
            }
            binding.txtTitle.text = item.title
            binding.icon.setImageResource(item.icon)
        }
    }




    fun submitList(items: List<WhosComing>) {
        diffUtil.submitList(items)
    }

}