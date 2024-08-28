package com.salihakbas.kekodprojectchallenge.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.isVisible
import com.salihakbas.kekodprojectchallenge.R
import com.salihakbas.kekodprojectchallenge.databinding.FragmentSwitchBinding
import com.salihakbas.kekodprojectchallenge.ui.SwitchListener
import com.salihakbas.kekodprojectchallenge.ui.activity.MainActivity


class SwitchFragment : Fragment(),SwitchListener {
    private lateinit var binding: FragmentSwitchBinding
    private val switchList: List<SwitchCompat>
        get() {
            binding.switch1
            binding.switch2
            binding.switch3
            binding.switch4
            binding.switch5
            return listOf(
                binding.switch1,
                binding.switch2, binding.switch3, binding.switch4, binding.switch5
            )
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSwitchBinding.inflate(inflater, container, false)

        // Başlangıçta ego switchi etkinleştir, diğer switchleri kapat
        initializeSwitches(switchList)
        // Ego switchinin açık olduğu durumlarda diğer switchleri kapat
        setOtherSwitchListeners(switchList)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity = activity as MainActivity
        mainActivity.getBottomNavigationView().visibility = View.GONE

        manageMenuItems(mainActivity)

        binding.scEgo.setOnCheckedChangeListener { _, isChecked ->
            setBottomNavVisibility(isChecked, switchList, mainActivity)
            if (isChecked) {
                binding.closeAnim.isVisible = true
                binding.closeAnim.playAnimation()
                binding.doneAnim.visibility = View.GONE
                binding.scEgo.thumbIconDrawable = context?.getDrawable(R.drawable.ic_done  )
                removeMenuItems(mainActivity)

            }else {
                binding.closeAnim.isVisible = false
                binding.doneAnim.visibility = View.VISIBLE
                binding.doneAnim.playAnimation()
                binding.scEgo.thumbIconDrawable = context?.getDrawable(R.drawable.ic_close)
            }
        }
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

    private fun setBottomNavVisibility(
        isChecked: Boolean,
        switchList: List<SwitchCompat>,
        activity: MainActivity
    ) {
        activity.getBottomNavigationView().visibility = if (isChecked) {
            disableOtherSwitches(switchList)
            View.GONE
        } else {
            enableOtherSwitches(switchList)
            View.VISIBLE
        }
    }

    private fun manageMenuItems(activity: MainActivity) {
        val switchToMenuItemIdMap = mapOf(
            R.id.switch1 to R.id.happinessFragment,
            R.id.switch2 to R.id.optimismFragment,
            R.id.switch3 to R.id.kindnessFragment,
            R.id.switch4 to R.id.givingFragment,
        )

        for ((switchId, menuItemId) in switchToMenuItemIdMap) {
            val switch = binding.root.findViewById<SwitchCompat>(switchId)
            switch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    val title = switch.text.toString()
                    activity.addMenuItem(title, menuItemId)
                } else {
                    activity.removeMenuItem(menuItemId)
                }
            }
        }
    }

    private fun removeMenuItems(activity: MainActivity) {
        val ids = listOf(R.id.happinessFragment, R.id.optimismFragment, R.id.kindnessFragment, R.id.givingFragment)
        for (id in ids) {
            activity.removeMenuItem(id)
        }
    }

}