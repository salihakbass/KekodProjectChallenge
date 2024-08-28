package com.salihakbas.kekodprojectchallenge.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.salihakbas.kekodprojectchallenge.databinding.FragmentGivingBinding


class GivingFragment : Fragment() {
    private var score = 0
    private var timeLeft = 15
    private var gameStarted = false
    private val handler = Handler(Looper.getMainLooper())
    private val imageViews = mutableListOf<ImageView>()
    private lateinit var binding: FragmentGivingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGivingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageViews.addAll(
            listOf(
                binding.imgKekod1,
                binding.imgKekod2,
                binding.imgKekod3,
                binding.imgKekod4,
                binding.imgKekod5,
                binding.imgKekod6,
                binding.imgKekod7,
                binding.imgKekod8,
                binding.imgKekod9
            )
        )


        imageViews.forEach { imageView ->
            imageView.setOnClickListener {
                if (imageView.visibility == View.VISIBLE) {
                    score++
                    binding.scoreTextView.text = "Score: $score"
                    imageView.visibility = View.GONE
                }
            }
        }

        binding.startButton.setOnClickListener {
            if (!gameStarted) {
                startGame()
            }
        }
    }

    private fun startGame() {
        gameStarted = true
        score = 0
        timeLeft = 20
        binding.scoreTextView.text = "Score: $score"
        binding.timerTextView.text = "Time: $timeLeft"
        binding.startButton.visibility = View.GONE

        handler.postDelayed(object : Runnable {
            override fun run() {
                if (timeLeft > 0) {
                    timeLeft--
                    binding.timerTextView.text = "Time: $timeLeft"
                    showRandomImage()
                    handler.postDelayed(this, 500)
                } else {
                    endGame()
                }
            }
        }, 1000)
    }

    private fun showRandomImage() {
        imageViews.forEach { it.visibility = View.GONE }
        val randomImage = imageViews.random()
        randomImage.visibility = View.VISIBLE
    }

    private fun endGame() {
        gameStarted = false
        binding.startButton.visibility = View.VISIBLE
        imageViews.forEach { it.visibility = View.GONE }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacksAndMessages(null)
    }
}
