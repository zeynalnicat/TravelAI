package com.example.travelai.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.travelai.R
import com.example.travelai.databinding.FragmentSingleBinding
import com.example.travelai.domain.home.DiscoverCity


class SingleFragment : Fragment() {
    private lateinit var binding: FragmentSingleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingleBinding.inflate(inflater)
        setLayout()
        setNavigation()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var args = SingleFragmentArgs.fromBundle(requireArguments())
        val item = args.destination
        setLayout(item)


    }

    private fun setLayout(item:DiscoverCity){
        binding.imgBackground.setImageResource(item.img)
        binding.txtName.text = item.title
        binding.txtRating.text = item.rating.toString()
    }

    private fun setLayout() {
        binding.call.txtName.text = "Call"
        binding.call.imgIcon.setImageResource(R.drawable.icon_phone)

        binding.email.txtName.text = "Email"
        binding.email.imgIcon.setImageResource(R.drawable.icon_inbox)

        binding.review.txtName.text = "Review"
        binding.review.imgIcon.setImageResource(R.drawable.icon_message)
    }

    private fun setNavigation() {
        binding.navBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }


}