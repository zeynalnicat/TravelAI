package com.example.travelai.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.travelai.databinding.FragmentSingleBinding


class SingleFragment : Fragment() {
    private lateinit var binding:FragmentSingleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingleBinding.inflate(inflater)
        return binding.root
    }


}