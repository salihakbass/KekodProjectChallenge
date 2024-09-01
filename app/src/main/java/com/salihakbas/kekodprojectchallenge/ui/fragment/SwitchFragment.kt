package com.salihakbas.kekodprojectchallenge.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.isVisible
import androidx.core.view.size
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
            binding.scHappiness,
            binding.scOptimism,
            binding.scKindness,
            binding.scGiving,
            binding.scRespect
        )
        getAnimation()
        // Başlangıçta ego switchi etkinleştir, diğer switchleri kapat
        initializeSwitches(switchList)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavMenu = (activity as MainActivity).bottomNavmenu


        binding.scEgo.setOnCheckedChangeListener { _, isChecked ->
            (activity as? MainActivity)?.setBottomNavVisibility(!isChecked)
            if (isChecked) {
                binding.closeAnim.isVisible = true
                binding.closeAnim.playAnimation()
                binding.doneAnim.isVisible = false
                binding.scEgo.thumbIconDrawable = context?.getDrawable(R.drawable.ic_done)
                bottomNavMenu.removeItem(R.id.happinessFragment)
                bottomNavMenu.removeItem(R.id.optimismFragment)
                bottomNavMenu.removeItem(R.id.givingFragment)
                bottomNavMenu.removeItem(R.id.kindnessFragment)
                bottomNavMenu.removeItem(R.id.respectFragment)


                with(binding) {
                    scHappiness.isClickable = false
                    scOptimism.isClickable = false
                    scKindness.isClickable = false
                    scGiving.isClickable = false
                    scRespect.isClickable = false

                    scHappiness.isChecked = false
                    scOptimism.isChecked = false
                    scKindness.isChecked = false
                    scGiving.isChecked = false
                    scRespect.isChecked = false
                }
            } else {
                binding.closeAnim.isVisible = false
                binding.doneAnim.visibility = View.VISIBLE
                binding.doneAnim.playAnimation()
                binding.scEgo.thumbIconDrawable = context?.getDrawable(R.drawable.ic_close)
                enableOtherSwitches(switchList)
            }
        }

        binding.scGiving.setOnCheckedChangeListener { _, isChecked ->
            manageMenuItems(
                bottomNavMenu,
                isChecked,
                R.id.givingFragment,
                "Giving",
                R.drawable.game1
            )
        }
        binding.scHappiness.setOnCheckedChangeListener { _, isChecked ->
            manageMenuItems(
                bottomNavMenu,
                isChecked,
                R.id.happinessFragment,
                "Happiness",
                R.drawable.game2
            )
        }
        binding.scKindness.setOnCheckedChangeListener { _, isChecked ->
            manageMenuItems(
                bottomNavMenu,
                isChecked,
                R.id.kindnessFragment,
                "Kindness",
                R.drawable.game3
            )

        }
        binding.scOptimism.setOnCheckedChangeListener { _, isChecked ->
            manageMenuItems(
                bottomNavMenu,
                isChecked,
                R.id.optimismFragment,
                "Optimism",
                R.drawable.game4
            )
        }
        binding.scRespect.setOnCheckedChangeListener { _, isChecked ->
            manageMenuItems(
                bottomNavMenu,
                isChecked,
                R.id.respectFragment,
                "Respect",
                R.drawable.game5
            )
        }


    }

    private fun manageMenuItems(
        menu: Menu,
        isChecked: Boolean,
        fragmentId: Int,
        title: String,
        bottomIcon: Int,

        ) {

        if (menu.size <= 4 && isChecked && menu.findItem(fragmentId) == null) {
            menu.add(Menu.NONE, fragmentId, Menu.NONE, title)
                ?.setIcon(bottomIcon)
        } else if (!isChecked && menu.findItem(fragmentId) != null) {
            menu.removeItem(fragmentId)
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


    fun getAnimation() {
        val topAnimation = AnimationUtils.loadAnimation(context, R.anim.top_animation)
        val bottomAnimation = AnimationUtils.loadAnimation(context, R.anim.bottom_animation)
        binding.scEgo.startAnimation(bottomAnimation)
        binding.scRespect.startAnimation(bottomAnimation)
        binding.scGiving.startAnimation(bottomAnimation)
        binding.scKindness.startAnimation(topAnimation)
        binding.scHappiness.startAnimation(topAnimation)
        binding.scOptimism.startAnimation(topAnimation)
    }


}