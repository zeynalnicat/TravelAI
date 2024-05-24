package com.example.travelai.ui.fragments.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelai.R
import com.example.travelai.databinding.FragmentHomeBinding
import com.example.travelai.domain.home.DiscoverCity
import com.example.travelai.domain.home.MoreCity
import com.example.travelai.domain.home.SearchItem
import com.example.travelai.ui.activity.MainActivity
import com.example.travelai.ui.fragments.home.adapters.DiscoverCityAdapter
import com.example.travelai.ui.fragments.home.adapters.MoreCityAdapter
import com.example.travelai.ui.fragments.home.adapters.SearchItemAdapter


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var startY = 0f
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        val activityMain = requireActivity() as MainActivity
        activityMain.setVisibility(true)
        setAdapter()
        setDiscoverCitySection()
        setMoreDiscoverSection()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        motionLayout()
    }

    fun setAdapter() {
        val items = listOf(
            SearchItem(R.drawable.icon_pizza, "Restaurants"),
            SearchItem(R.drawable.icon_flash, "Things to do"),
            SearchItem(R.drawable.icon_hotels, "Hotels"),
            SearchItem(R.drawable.icon_flash, "Things to do"),
        )

        val adapter = SearchItemAdapter()
        adapter.submitList(items)
        binding.recyclerViewSearch.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerViewSearch.adapter = adapter
    }

    @SuppressLint("ClickableViewAccessibility")
    fun motionLayout() {
        binding.motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {
            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {

            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                if (currentId == R.id.end) {
                    binding.recyclerViewSearch.visibility = View.GONE
                } else {
                    binding.recyclerViewSearch.visibility = View.VISIBLE
                }
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {
            }
        })

        binding.motionLayout.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> startY = event.y
                MotionEvent.ACTION_MOVE -> {
                    if (event.y - startY > 0) {
                        binding.recyclerViewSearch.visibility = View.VISIBLE
                    } else {
                        binding.recyclerViewSearch.visibility = View.GONE
                    }
                }
            }
            false
        }

    }


    fun setDiscoverCitySection() {
        val listItems = listOf(
            DiscoverCity(R.drawable.food, "Food", "Qaynana Restaurant", 4.7),
            DiscoverCity(R.drawable.food, "Food", "Qaynana Restaurant", 4.7),
            DiscoverCity(R.drawable.food, "Food", "East Town", 3.2),
            DiscoverCity(R.drawable.food, "Food", "Borani", 2.5),
            DiscoverCity(R.drawable.food, "Food", "China Town", 1.7),
            DiscoverCity(R.drawable.food, "Food", "KFC", 4.7),
        )

//        findNavController().navigate(action)

        val adapter =
            DiscoverCityAdapter { destionation ->
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToSingleFragment(destionation)
                )
            }
        adapter.submitList(listItems)
        binding.recyclerViewDiscover.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewDiscover.adapter = adapter

    }

    fun setMoreDiscoverSection() {
        val screenWidth = resources.displayMetrics.widthPixels
        val listItems = listOf(
            MoreCity("Sheki Azerbaijan", R.drawable.mountain),
            MoreCity("Quba Azerbaijan", R.drawable.mountain),
            MoreCity("Qusar Azerbaijan", R.drawable.mountain),
            MoreCity("Qebele Azerbaijan", R.drawable.mountain),
            MoreCity("Shamaxi Azerbaijan", R.drawable.mountain),
            MoreCity("Qazax Azerbaijan", R.drawable.mountain),
            MoreCity("Qax Azerbaijan", R.drawable.mountain),
        )

        val adapter = MoreCityAdapter(screenWidth = screenWidth)
        adapter.submitList(listItems)
        binding.recyclerViewMoreDiscover.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewMoreDiscover.adapter = adapter
    }


}