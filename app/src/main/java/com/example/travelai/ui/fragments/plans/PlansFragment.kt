package com.example.travelai.ui.fragments.plans

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.travelai.R
import com.example.travelai.databinding.FragmentPlansBinding
import com.example.travelai.ui.activity.MainActivity

class PlansFragment : Fragment() {

    private lateinit var binding: FragmentPlansBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlansBinding.inflate(inflater)
        val activityMain = requireActivity() as MainActivity
        activityMain.setVisibility(true)

        setNavigation()

        setLayout()
        return binding.root
    }


    private fun setLayout() {
        binding.planOwn.txtWithAi.visibility = View.GONE
        binding.planOwn.cardView.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.button))
        binding.planOwn.txtDescription.setTextColor(requireContext().getColor(R.color.white))
        binding.planOwn.txtDescription.setTextSize(24f)
        binding.planOwn.txtDescription.setText(R.string.create_plan_own)
        binding.planOwn.imgLeft.setImageResource(R.drawable.rectangle_left)
        binding.planOwn.imgRight.setImageResource(R.drawable.rectangle_right)
    }


    private fun setNavigation() {
        binding.planAI.btnCreate.setOnClickListener {
            findNavController().navigate(R.id.action_plansFragment_to_buildPlanFragment)
        }
    }
}