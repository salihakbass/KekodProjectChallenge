package com.salihakbas.kekodprojectchallenge.ui.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.salihakbas.kekodprojectchallenge.R
import com.salihakbas.kekodprojectchallenge.databinding.FragmentTabABinding


class TabAFragment : Fragment() {
    private lateinit var binding: FragmentTabABinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topAnimation = android.view.animation.AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.top_animation
        )
        val bottomAnimation = android.view.animation.AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.bottom_animation
        )
        val stbAnimation = android.view.animation.AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.stb_anim
        )

        binding.ivKekod.startAnimation(stbAnimation)
        binding.tvDesc.startAnimation(bottomAnimation)


    }

}