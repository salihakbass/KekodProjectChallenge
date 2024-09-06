package com.salihakbas.kekodprojectchallenge.ui.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.salihakbas.kekodprojectchallenge.R
import com.salihakbas.kekodprojectchallenge.databinding.FragmentTabABinding
import com.salihakbas.kekodprojectchallenge.databinding.FragmentTabBBinding


class TabBFragment : Fragment() {
    private lateinit var binding: FragmentTabBBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnStart.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_splashFragment_to_switchFragment)
        }
    }

}