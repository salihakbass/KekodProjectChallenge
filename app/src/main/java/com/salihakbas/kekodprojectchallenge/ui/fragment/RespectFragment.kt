package com.salihakbas.kekodprojectchallenge.ui.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.salihakbas.kekodprojectchallenge.databinding.FragmentRespectBinding

class RespectFragment : Fragment() {

    private var _binding: FragmentRespectBinding? = null
    private val binding get() = _binding!!

    private val words = listOf("KEKOD", "ANDROİD", "KOTLİN", "JETPACK", "BİNDİNG", "RETROFİT", "FRAGMENT", "ACTİVİTY", "VİEWMODEL", "MVVM", "ROOM", "JAVA", "COMPOSE", "XML", "DATA", "REPOSİTORY")
    private var usedWords = mutableSetOf<String>()
    private var currentWord = ""
    private lateinit var timer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRespectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupGame()

        binding.btnSubmit.setOnClickListener {
            checkGuess()
        }

        binding.btnRestart.setOnClickListener {
            restartGame()
        }
    }

    private fun setupGame() {
        if (usedWords.size == words.size) {
            endGame()
            return
        }

        currentWord = words.filter { it !in usedWords }.random()
        usedWords.add(currentWord)
        binding.tvScrambledWord.text = currentWord.toCharArray().apply { shuffle() }.concatToString()
        binding.tvResult.text = ""
        binding.etGuess.text.clear()
        startTimer()
    }

    private fun startTimer() {
        timer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tvTimer.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                binding.tvResult.text = "Süre doldu!"
                showCorrectWord()
            }
        }.start()
    }

    private fun checkGuess() {
        val guess = binding.etGuess.text.toString()
        if (guess.equals(currentWord, ignoreCase = true)) {
            binding.tvResult.text = "Doğru!"
            timer.cancel()
            showCorrectWord()
        } else {
            binding.tvResult.text = "Tekrar Dene!"
        }
    }

    private fun showCorrectWord() {
        binding.tvScrambledWord.text = currentWord
        object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                setupGame()
            }
        }.start()
    }

    private fun endGame() {
        binding.tvResult.text = "Oyun Bitti!"
        binding.btnRestart.visibility = View.VISIBLE
    }

    private fun restartGame() {
        usedWords.clear()
        binding.btnRestart.visibility = View.GONE
        setupGame()
    }
}