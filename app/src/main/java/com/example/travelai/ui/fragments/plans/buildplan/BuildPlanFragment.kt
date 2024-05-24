package com.example.travelai.ui.fragments.plans.buildplan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.travelai.R
import com.example.travelai.databinding.FragmentBuildPlanBinding
import com.example.travelai.domain.home.MoreCity
import com.example.travelai.ui.activity.MainActivity
import com.example.travelai.ui.fragments.home.adapters.MoreCityAdapter


class BuildPlanFragment : Fragment() {
    private lateinit var binding: FragmentBuildPlanBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBuildPlanBinding.inflate(inflater)
        val activityMain = requireActivity() as MainActivity
        activityMain.setVisibility(false)
        setNavigation()
        setAdapter()
        return binding.root
    }


    private fun setAdapter() {

        val screenWidth = resources.displayMetrics.widthPixels
        val items = listOf(
            MoreCity("Kyoto Japan", R.drawable.spain, true),
            MoreCity("Cape Town South Africa", R.drawable.mountain, true),
            MoreCity("Barcelona Spain", R.drawable.spain, true),
            MoreCity("Reykjavik Iceland", R.drawable.mountain, true),
            MoreCity("Kyoto Japan", R.drawable.mountain, true),
            MoreCity("Kyoto Japan", R.drawable.spain, true),
            MoreCity("Kyoto Japan", R.drawable.mountain, true),
            MoreCity("Kyoto Japan", R.drawable.spain, true),
        )
        val adapter =
            MoreCityAdapter ({ findNavController().navigate(R.id.action_buildPlanFragment_to_whosComingFragment) }, screenWidth = screenWidth)
        adapter.submitList(items)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter

    }


    private fun setNavigation(){
        binding.btnClose.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}