package com.example.travelai.ui.fragments.plans.buildplan

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.travelai.R
import com.example.travelai.databinding.FragmentWhosComingBinding
import com.example.travelai.domain.plans.WhosComing
import com.example.travelai.ui.activity.MainActivity
import com.example.travelai.ui.fragments.plans.buildplan.adapters.WhosComingAdapter


class WhosComingFragment : Fragment() {
    private lateinit var binding: FragmentWhosComingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWhosComingBinding.inflate(inflater)
        val activityMain = requireActivity() as MainActivity
        activityMain.setVisibility(false)
        setAnimation()
        setNavigation()
        setAdapter()
        return binding.root
    }


    private fun setAdapter() {

        val screenWidth = resources.displayMetrics.widthPixels
        val items = listOf(
            WhosComing("Solo", R.drawable.icon_profile),
            WhosComing("Partner", R.drawable.icon_lovely),
            WhosComing("Friend", R.drawable.icon_2user),
            WhosComing("Family", R.drawable.icon_people),

            )
        val adapter = WhosComingAdapter({findNavController().navigate(R.id.action_whosComingFragment_to_selectTimeFragment)},screenWidth)
        adapter.submitList(items)

        binding.recyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerview.adapter = adapter
    }


    private fun setAnimation(){

        val anim = ObjectAnimator.ofFloat(binding.recyclerview,"translationY",200f ,0f).apply {
            duration = 400
        }

        val anim2 = ObjectAnimator.ofFloat(binding.recyclerview,"translationX",-200f , 0f).apply {
            duration = 400
        }
        val animatorSet = AnimatorSet().apply {
            playTogether(anim, anim2)
        }

        animatorSet.start()
    }

    private fun setNavigation() {
        binding.btnClose.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}