package com.salihakbas.kekodprojectchallenge.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.salihakbas.kekodprojectchallenge.R
import com.salihakbas.kekodprojectchallenge.databinding.FragmentSwitchBinding
import com.salihakbas.kekodprojectchallenge.ui.SwitchListener
import com.salihakbas.kekodprojectchallenge.ui.activity.MainActivity


class SwitchFragment : Fragment(), SwitchListener {
    lateinit var binding: FragmentSwitchBinding
    private lateinit var switchList: List<SwitchCompat>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSwitchBinding.inflate(inflater, container, false)
        switchList = listOf(
            binding.scHappiness, binding.scOptimism, binding.scKindness, binding.scGiving, binding.scRespect
        )

        // Başlangıçta ego switchi etkinleştir, diğer switchleri kapat
        initializeSwitches(switchList)
        // Ego switchinin açık olduğu durumlarda diğer switchleri kapat
        setOtherSwitchListeners(switchList)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val mainActivity = activity as MainActivity
        mainActivity.getBottomNavigationView().visibility = View.INVISIBLE



        binding.scEgo.setOnCheckedChangeListener { _, isChecked ->
            setBottomNavVisibility(isChecked, switchList, mainActivity)
            if (isChecked) {
                binding.closeAnim.isVisible = true
                binding.closeAnim.playAnimation()
                binding.doneAnim.visibility = View.GONE
                binding.scEgo.thumbIconDrawable = context?.getDrawable(R.drawable.ic_done)
                disableOtherSwitches(switchList)
                removeMenuItems(mainActivity)

            } else {
                binding.closeAnim.isVisible = false
                binding.doneAnim.visibility = View.VISIBLE
                binding.doneAnim.playAnimation()
                manageMenuItems(mainActivity)
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

    fun setBottomNavVisibility(
        isChecked: Boolean, switchList: List<SwitchCompat>, activity: MainActivity
    ) {
        activity.getBottomNavigationView().visibility = if (isChecked) {
            disableOtherSwitches(switchList)
            View.INVISIBLE
        } else {
            enableOtherSwitches(switchList)
            View.VISIBLE
        }
    }

    fun manageMenuItems(activity: MainActivity) {
        val switchToMenuItemIdMap = mapOf(
            R.id.scHappiness to Pair(R.id.happinessFragment, R.drawable.ic_search),
            R.id.scOptimism to Pair(R.id.optimismFragment, R.drawable.ic_cart),
            R.id.scKindness to Pair(R.id.kindnessFragment, R.drawable.ic_favorite),
            R.id.scGiving to Pair(R.id.givingFragment, R.drawable.ic_profile),
            R.id.scRespect to Pair(R.id.respectFragment, R.drawable.ic_profile)
        )

        for ((switchId, menuItemId) in switchToMenuItemIdMap) {
            val switch = binding.root.findViewById<SwitchCompat>(switchId)
            switch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    if (activity.getBottomNavigationView().menu.size() < 5) {
                        val (fragmentId, iconResId) = menuItemId
                        val title = switch.text.toString()
                        activity.addMenuItem(title, fragmentId, iconResId)
                    } else {
                        Toast.makeText(context, "Bottom navigation can only have 5 items", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val (fragmentId, _) = menuItemId
                    activity.removeMenuItem(fragmentId)
                }
            }
        }
    }

    fun removeMenuItems(activity: MainActivity) {
        val ids = listOf(
            R.id.happinessFragment,
            R.id.optimismFragment,
            R.id.kindnessFragment,
            R.id.givingFragment,
            R.id.respectFragment
        )
        for (id in ids) {
            activity.removeMenuItem(id)
        }
    }

}