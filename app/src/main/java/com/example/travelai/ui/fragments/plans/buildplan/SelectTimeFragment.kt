package com.example.travelai.ui.fragments.plans.buildplan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.travelai.R

import com.example.travelai.databinding.FragmentSelectTimeBinding
import com.example.travelai.domain.search.SearchItem
import com.example.travelai.ui.fragments.search.adapters.RecentlySearchedAdapter

class SelectTimeFragment : Fragment() {
    private lateinit var binding: FragmentSelectTimeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectTimeBinding.inflate(inflater)
        setNavigation()
        setAdapter()
        return binding.root
    }

    private fun setAdapter() {
        val items = listOf(
            SearchItem("1 hour",true),
            SearchItem("3 hour",true),
            SearchItem("1 hour 30 min",true),
            SearchItem("3 hour 30 min",true),
            SearchItem("2 hour",true),
            SearchItem("4 hour",true),
            SearchItem("2 hour 30 min",true),
            SearchItem("4 hour 30 min",true)
        )
        val adapter =
            RecentlySearchedAdapter { findNavController().navigate(R.id.action_selectTimeFragment_to_selectActivitiesFragment) }
        adapter.submitList(items)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter
    }


    private fun setNavigation() {
        binding.btnClose.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}