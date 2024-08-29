package com.salihakbas.kekodprojectchallenge.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.salihakbas.kekodprojectchallenge.databinding.FragmentKindnessBinding

class KindnessFragment : Fragment() {
    private lateinit var binding: FragmentKindnessBinding
    private val words = listOf(
        "Kekod",
        "Android",
        "Kotlin",
        "Jetpack",
        "Binding",
        "Retrofit",
        "Fragment",
        "Activity",
        "Viewmodel",
        "Mvvm",
        "Room",
        "Java",
        "Compose",
        "Xml",
        "Data",
        "Repository"
    )
    private var currentWord = ""
    private var currentWordIndex = 0
    private var currentDisplay = ""
    private var score = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentKindnessBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startNewGame()

        binding.guessButton.setOnClickListener {
            val userGuess = binding.guessEditText.text.toString()
            checkGuess(userGuess)
        }

        binding.restartButton.setOnClickListener {
            startNewGame()
        }
    }

    private fun startNewGame() {
        currentWordIndex = 0
        score = 0
        loadNextWord()
    }

    private fun loadNextWord() {
        if (currentWordIndex < words.size) {
            currentWord = words[currentWordIndex]
            currentDisplay = currentWord[0] + "_".repeat(currentWord.length - 1)
            currentWordIndex++
            binding.wordTextView.text = currentDisplay
        } else {
            showEndGame()
        }
    }

    private fun checkGuess(userGuess: String) {
        if (userGuess.equals(currentWord, ignoreCase = true)) {
            score += currentDisplay.count { it == '_' }
            binding.scoreTextView.text = "Skor: $score"
            loadNextWord()
        } else {
            revealNextLetter()
        }
        binding.wordTextView.text = currentDisplay
        binding.guessEditText.text.clear()
    }

    private fun revealNextLetter() {
        for (i in currentDisplay.indices) {
            if (currentDisplay[i] == '_') {
                currentDisplay = currentDisplay.substring(
                    0,
                    i
                ) + currentWord[i] + currentDisplay.substring(i + 1)
                break
            }
        }
    }

    private fun showEndGame() {
        Toast.makeText(context, "Oyun Bitti ! Skorunuz: $score", Toast.LENGTH_LONG).show()
        binding.restartButton.visibility = View.VISIBLE
    }

}