package com.example.travelai.ui.fragments.search

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelai.R
import com.example.travelai.databinding.FragmentSearchBinding
import com.example.travelai.domain.home.MoreCity
import com.example.travelai.ui.fragments.home.adapters.MoreCityAdapter
import com.example.travelai.ui.fragments.search.adapters.RecentlySearchedAdapter
import com.example.travelai.util.CustomLayoutManager


class SearchFragment : Fragment() {
    private lateinit var binding : FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)
        setAnimation()
        setRecentlySearched()
        setTopDestination()
        return binding.root
    }


    fun setRecentlySearched(){
        val items = listOf("Egypt","Orlando","Poland","Morocco","Canada")
        val adapter = RecentlySearchedAdapter()
        adapter.submitList(items)
        binding.recyclerRecentlySearched.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerRecentlySearched.adapter = adapter

    }


    fun setAnimation(){
        val anim = ObjectAnimator.ofFloat(binding.cardView, "translationY", 220f, 0f).apply {
            duration = 800

        }
        anim.start()

    }

    fun setTopDestination(){
        val listItems = listOf(
            MoreCity("Sheki Azerbaijan", R.drawable.mountain, true),
            MoreCity("Quba Azerbaijan", R.drawable.mountain,true),
            MoreCity("Qusar Azerbaijan", R.drawable.mountain,true),
            MoreCity("Qebele Azerbaijan", R.drawable.mountain,true),
            MoreCity("Shamaxi Azerbaijan", R.drawable.mountain,true),
            MoreCity("Qazax Azerbaijan", R.drawable.mountain,true),
            MoreCity("Qax Azerbaijan", R.drawable.mountain,true),
        )

        val adapter = MoreCityAdapter()
        adapter.submitList(listItems)
        val layoutManager = GridLayoutManager(requireContext(),2)
        binding.recyclerTopDestination.layoutManager = layoutManager
        binding.recyclerTopDestination.adapter = adapter

    }

}