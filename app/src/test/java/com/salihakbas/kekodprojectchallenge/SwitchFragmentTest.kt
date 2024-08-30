package com.salihakbas.kekodprojectchallenge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.materialswitch.MaterialSwitch
import com.salihakbas.kekodprojectchallenge.databinding.FragmentSwitchBinding
import com.salihakbas.kekodprojectchallenge.ui.activity.MainActivity
import com.salihakbas.kekodprojectchallenge.ui.fragment.SwitchFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SwitchFragmentTest {

    @Mock
    private lateinit var binding: FragmentSwitchBinding

    @Mock
    private lateinit var inflater: LayoutInflater

    @Mock
    private lateinit var container: ViewGroup

    @Mock
    private lateinit var mainActivity: MainActivity

    @Mock
    private lateinit var switch1: MaterialSwitch

    @Mock
    private lateinit var switch2: MaterialSwitch

    @Mock
    private lateinit var switch3: MaterialSwitch

    @Mock
    private lateinit var switch4: MaterialSwitch

    @Mock
    private lateinit var switch5: MaterialSwitch

    private lateinit var switchFragment: SwitchFragment

    @Before
    fun setUp() {
        switchFragment = SwitchFragment()
        switchFragment.binding = binding
        `when`(binding.switch1).thenReturn(switch1)
        `when`(binding.switch2).thenReturn(switch2)
        `when`(binding.switch3).thenReturn(switch3)
        `when`(binding.switch4).thenReturn(switch4)
        `when`(binding.switch5).thenReturn(switch5)
        `when`(mainActivity.getBottomNavigationView()).thenReturn(mock(View::class.java) as BottomNavigationView?)
    }

    @Test
    fun testOnCreateView() {
        switchFragment.onCreateView(inflater, container, null)
        verify(binding).switch1
        verify(binding).switch2
        verify(binding).switch3
        verify(binding).switch4
        verify(binding).switch5
    }

    @Test
    fun testSetOtherSwitchListeners() {
        val switchList = listOf(switch1, switch2, switch3, switch4, switch5)
        switchFragment.setOtherSwitchListeners(switchList)
        for (switch in switchList) {
            verify(switch).setOnCheckedChangeListener(any())
        }
    }

    @Test
    fun testEnableOtherSwitches() {
        val switchList = listOf(switch1, switch2, switch3, switch4, switch5)
        switchFragment.enableOtherSwitches(switchList)
        for (switch in switchList) {
            verify(switch).isClickable = true
        }
    }

    @Test
    fun testDisableOtherSwitches() {
        val switchList = listOf(switch1, switch2, switch3, switch4, switch5)
        switchFragment.disableOtherSwitches(switchList)
        for (switch in switchList) {
            verify(switch).isChecked = false
            verify(switch).isClickable = false
        }
    }

    @Test
    fun testInitializeSwitches() {
        val switchList = listOf(switch1, switch2, switch3, switch4, switch5)
        switchFragment.initializeSwitches(switchList)
        verify(binding.scEgo).isChecked = true
        for (switch in switchList) {
            verify(switch).isChecked = false
            verify(switch).isClickable = false
        }
    }

    @Test
    fun testSetBottomNavVisibility() {
        val switchList = listOf(switch1, switch2, switch3, switch4, switch5)
        switchFragment.setBottomNavVisibility(true, switchList, mainActivity)
        verify(mainActivity).getBottomNavigationView().visibility = View.INVISIBLE
        switchFragment.setBottomNavVisibility(false, switchList, mainActivity)
        verify(mainActivity).getBottomNavigationView().visibility = View.VISIBLE
    }

    @Test
    fun testManageMenuItems() {
        switchFragment.manageMenuItems(mainActivity)
        verify(binding.switch1).setOnCheckedChangeListener(any())
        verify(binding.switch2).setOnCheckedChangeListener(any())
        verify(binding.switch3).setOnCheckedChangeListener(any())
        verify(binding.switch4).setOnCheckedChangeListener(any())
    }

    @Test
    fun testRemoveMenuItems() {
        switchFragment.removeMenuItems(mainActivity)
        verify(mainActivity).removeMenuItem(R.id.happinessFragment)
        verify(mainActivity).removeMenuItem(R.id.optimismFragment)
        verify(mainActivity).removeMenuItem(R.id.kindnessFragment)
        verify(mainActivity).removeMenuItem(R.id.givingFragment)
    }
}