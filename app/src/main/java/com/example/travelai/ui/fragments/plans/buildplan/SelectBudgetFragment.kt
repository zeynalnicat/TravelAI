package com.example.travelai.ui.fragments.plans.buildplan

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.travelai.R
import com.example.travelai.databinding.FragmentSelectBudgetBinding
import com.example.travelai.ui.fragments.plans.buildplan.viewmodel.SelectBudgetViewModel
import com.google.android.material.animation.AnimatorSetCompat.playTogether


class SelectBudgetFragment : Fragment() {
    private lateinit var binding: FragmentSelectBudgetBinding
    private val viewModel: SelectBudgetViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectBudgetBinding.inflate(inflater)
        setAnimation()
        setNavigation()

        binding.btnPlus.setOnClickListener {
            viewModel.increase()
        }

        binding.btnMinus.setOnClickListener {
            viewModel.decrease()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.total.observe(viewLifecycleOwner) {
            binding.txtAmount.text = it.toString()
        }
    }

    private fun setNavigation() {
        binding.btnClose.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setAnimation() {
        val anim = ObjectAnimator.ofFloat(binding.btnMinus, "translationX", -200f, 0f).apply {
            duration = 500
        }
        val anim2 = ObjectAnimator.ofFloat(binding.btnPlus, "translationX", 200f, 0f).apply {
            duration = 500
        }
        val anim3 = ObjectAnimator.ofFloat(binding.txtHeader, "translationY", 200f, 0f).apply {
            duration = 500
        }
        val animatorSet = AnimatorSet().apply {
            playTogether(anim, anim2, anim3)
        }

        animatorSet.start()
    }


}