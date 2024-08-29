package com.salihakbas.kekodprojectchallenge.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.salihakbas.kekodprojectchallenge.R
import com.salihakbas.kekodprojectchallenge.databinding.FragmentSplashBinding
import com.salihakbas.kekodprojectchallenge.ui.activity.MainActivity


class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding

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
        val topAnimation = android.view.animation.AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.top_animation
        )
        val bottomAnimation = android.view.animation.AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.bottom_animation
        )
        val mainActivity = activity as MainActivity
        mainActivity.getBottomNavigationView().visibility = View.GONE
        binding.ivKekod.startAnimation(topAnimation)
        binding.btnStart.startAnimation(bottomAnimation)
        binding.tvDesc.startAnimation(bottomAnimation)

        binding.btnStart.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_splashFragment_to_switchFragment)
        }
    }

}