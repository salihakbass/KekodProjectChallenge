package com.salihakbas.kekodprojectchallenge.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.salihakbas.kekodprojectchallenge.R
import com.salihakbas.kekodprojectchallenge.databinding.FragmentHappinessBinding
import kotlin.random.Random


class HappinessFragment : Fragment() {
    private lateinit var binding: FragmentHappinessBinding

    private var randomNumber = 0
    private var attemptsLeft = 5
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHappinessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        randomNumber = Random.nextInt(1, 51)

        binding.guessButton.setOnClickListener {
            val userGuess = binding.guessInput.text.toString().toIntOrNull()
            if (userGuess != null) {
                checkGuess(userGuess)
            } else {
                Toast.makeText(context, "Lütfen bir sayı girin", Toast.LENGTH_SHORT).show()
            }
        }

        binding.restartButton.setOnClickListener {
            resetGame()
        }
    }

    private fun resetGame() {
        randomNumber = Random.nextInt(1, 51)
        attemptsLeft = 5
        binding.guessButton.isEnabled = true
        binding.restartButton.visibility = View.GONE
        binding.resultText.text = ""
        binding.guessInput.text.clear()
        binding.winnerAnim.visibility = View.GONE
        binding.lostAnim.visibility = View.GONE
        binding.thinkAnim.visibility = View.VISIBLE
        binding.upAnim.visibility = View.GONE
        binding.downAnim.visibility = View.GONE
    }

    private fun checkGuess(guess: Int) {
        if (guess == randomNumber) {
            binding.resultText.text = "Tebrikler! Doğru tahmin!"
            binding.upAnim.visibility = View.GONE
            binding.downAnim.visibility = View.GONE
            binding.thinkAnim.visibility = View.GONE
            binding.winnerAnim.visibility = View.VISIBLE
            binding.winnerAnim.playAnimation()
            endGame()
        } else {
            attemptsLeft--
            if (attemptsLeft > 0) {
                if (guess < randomNumber) {
                    binding.downAnim.visibility = View.GONE
                    binding.upAnim.visibility = View.VISIBLE
                    binding.upAnim.playAnimation()
                } else {
                    binding.upAnim.visibility = View.GONE
                    binding.downAnim.visibility = View.VISIBLE
                    binding.downAnim.playAnimation()
                }
            } else {
                binding.resultText.text = "Kaybettiniz! Doğru sayı $randomNumber idi."
                binding.thinkAnim.visibility = View.GONE
                binding.lostAnim.visibility = View.VISIBLE
                binding.lostAnim.playAnimation()
                endGame()
            }
        }
    }

    private fun endGame() {
        binding.guessButton.isEnabled = false
        binding.restartButton.visibility = View.VISIBLE
    }

}