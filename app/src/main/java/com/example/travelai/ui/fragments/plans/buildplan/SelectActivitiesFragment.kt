package com.example.travelai.ui.fragments.plans.buildplan

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travelai.R
import com.example.travelai.databinding.FragmentSelectActivitiesBinding
import com.example.travelai.domain.home.MoreCity
import com.example.travelai.ui.fragments.home.adapters.MoreCityAdapter


class SelectActivitiesFragment : Fragment() {
    private lateinit var binding: FragmentSelectActivitiesBinding
    private lateinit var adapter: MoreCityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectActivitiesBinding.inflate(inflater)
        setAnimation()
        setNavigation()
        setAdapter()

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun setAdapter() {
        val lists = listOf(
            MoreCity("Restaurants", R.drawable.food, true, isBuildPlan = true),
            MoreCity("Luxury Hotels", R.drawable.food, true, isBuildPlan = true),
            MoreCity("Cultural Experiences", R.drawable.food, true, isBuildPlan = true),
            MoreCity("Shopping", R.drawable.food, true, isBuildPlan = true),
            MoreCity("Art Museums", R.drawable.food, true, isBuildPlan = true),
            MoreCity("River Cruises", R.drawable.food, true, isBuildPlan = true),
            MoreCity("Restaurants", R.drawable.food, true, isBuildPlan = true),
            MoreCity("Restaurants", R.drawable.food, true, isBuildPlan = true),
            MoreCity("Restaurants", R.drawable.food, true, isBuildPlan = true),
        )

        adapter =
            MoreCityAdapter { findNavController().navigate(R.id.action_selectTimeFragment_to_selectActivitiesFragment) }

        adapter.submitList(lists)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter


    }

    private fun setNavigation() {
        binding.btnClose.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun setAnimation() {
        val anim = ObjectAnimator.ofFloat(binding.edtSearch, "translationY", 400f, 0f).apply {
            duration = 400
        }
        anim.start()
    }

}