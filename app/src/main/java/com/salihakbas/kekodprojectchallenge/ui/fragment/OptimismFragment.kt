package com.salihakbas.kekodprojectchallenge.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.salihakbas.kekodprojectchallenge.R
import com.salihakbas.kekodprojectchallenge.databinding.FragmentOptimismBinding


class OptimismFragment : Fragment() {
    private lateinit var binding: FragmentOptimismBinding

    private val buttons = Array(3) { arrayOfNulls<Button>(3) }
    private var playerTurn = true // true = kullanıcı, false = sistem


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOptimismBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonList = listOf(
            binding.button1,
            binding.button2,
            binding.button3,
            binding.button4,
            binding.button5,
            binding.button6,
            binding.button7,
            binding.button8,
            binding.button9
        )
        val fadeInAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in_anim)
        for (button in buttonList) {
            button.startAnimation(fadeInAnim)
        }

        buttons[0][0] = binding.button1
        buttons[0][1] = binding.button2
        buttons[0][2] = binding.button3
        buttons[1][0] = binding.button4
        buttons[1][1] = binding.button5
        buttons[1][2] = binding.button6
        buttons[2][0] = binding.button7
        buttons[2][1] = binding.button8
        buttons[2][2] = binding.button9


        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j]?.setOnClickListener {
                    onButtonClick(buttons[i][j]!!)
                }
            }
        }
    }

    private fun onButtonClick(button: Button) {
        if (button.text.isNotEmpty()) {
            return
        }

        if (playerTurn) {
            button.text = "X"
            if (checkForWin("X")) {
                Toast.makeText(context, "Kazandınız!", Toast.LENGTH_SHORT).show()
                resetBoard()
            } else {
                playerTurn = false
                systemTurn()
            }
        }
    }

    private fun systemTurn() {
        for (i in 0..2) {
            for (j in 0..2) {
                if (buttons[i][j]?.text.isNullOrEmpty()) {
                    buttons[i][j]?.text = "O"
                    if (checkForWin("O")) {
                        Toast.makeText(context, "Kaybettiniz!", Toast.LENGTH_SHORT).show()
                        resetBoard()
                    }
                    playerTurn = true
                    return
                }
            }
        }
    }

    private fun checkForWin(player: String): Boolean {
        for (i in 0..2) {
            if (buttons[i][0]?.text == player && buttons[i][1]?.text == player && buttons[i][2]?.text == player) {
                return true
            }
        }

        for (i in 0..2) {
            if (buttons[0][i]?.text == player && buttons[1][i]?.text == player && buttons[2][i]?.text == player) {
                return true
            }
        }

        if (buttons[0][0]?.text == player && buttons[1][1]?.text == player && buttons[2][2]?.text == player) {
            return true
        }

        if (buttons[0][2]?.text == player && buttons[1][1]?.text == player && buttons[2][0]?.text == player) {
            return true
        }

        return false
    }

    private fun resetBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j]?.text = ""
            }
        }
        playerTurn = true
    }
}

