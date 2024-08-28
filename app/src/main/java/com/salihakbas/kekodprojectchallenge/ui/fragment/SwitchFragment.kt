package com.salihakbas.kekodprojectchallenge.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import com.salihakbas.kekodprojectchallenge.R
import com.salihakbas.kekodprojectchallenge.databinding.FragmentSwitchBinding
import com.salihakbas.kekodprojectchallenge.ui.SwitchListener


class SwitchFragment : Fragment(),SwitchListener {
    private lateinit var binding: FragmentSwitchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSwitchBinding.inflate(inflater, container, false)
        return binding.root
    }


    // Ego switchinin açık olduğu durumlarda diğer switchleri kapat
    override fun setOtherSwitchListeners(switchList: List<SwitchCompat>) {
        for (switch in switchList) {
            switch.setOnCheckedChangeListener { _, isChecked ->
                if (binding.scEgo.isChecked) {
                    disableOtherSwitches(switchList)
                }
            }
        }
    }

    // Diğer switchleri tıklanabilir hale getir
    override fun enableOtherSwitches(switchList: List<SwitchCompat>) {
        for (switch in switchList) {
            switch.isClickable = true
        }
    }

    // Diğer switchleri tıklanamaz ve kapalı hale getir.
    override fun disableOtherSwitches(switchList: List<SwitchCompat>) {
        for (switch in switchList) {
            switch.isChecked = false
            switch.isClickable = false
        }
    }

    // Başlangıçta ego switchi etkinleştir, diğer switchleri kapat
    override fun initializeSwitches(switchList: List<SwitchCompat>) {
        binding.scEgo.isChecked = true
        disableOtherSwitches(switchList)
    }

}