package com.salihakbas.kekodprojectchallenge.ui.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.salihakbas.kekodprojectchallenge.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private lateinit var adapter: SplashFragmentStateAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPagerAdapter()
    }
    private val fragmentList = arrayListOf(
        TabAFragment(),TabBFragment()
    )
    private fun initViewPagerAdapter(){
        // Adapter init
        val viewPager = binding.viewPager2
        adapter = SplashFragmentStateAdapter(childFragmentManager, viewLifecycleOwner.lifecycle,fragmentList)
        viewPager.adapter = adapter
        binding.wormDotsIndicator.attachTo(binding.viewPager2)
    }

}