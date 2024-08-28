package com.salihakbas.kekodprojectchallenge.ui

import androidx.appcompat.widget.SwitchCompat

interface SwitchListener {

        fun setOtherSwitchListeners(switchList: List<SwitchCompat>)

        fun enableOtherSwitches(switchList: List<SwitchCompat>)

        fun disableOtherSwitches(switchList: List<SwitchCompat>)

        fun initializeSwitches(switchList : List<SwitchCompat>)


}