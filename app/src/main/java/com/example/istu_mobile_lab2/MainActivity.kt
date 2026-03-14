package com.example.istu_mobile_lab2

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var authorTextView: TextView
    private lateinit var previousButton: Button
    private lateinit var nextButton: Button

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        titleTextView = findViewById(R.id.titleTextView)
        authorTextView = findViewById(R.id.authorTextView)
        previousButton = findViewById(R.id.previousButton)
        nextButton = findViewById(R.id.nextButton)

        // Восстановление состояния при повороте
        savedInstanceState?.let {
            currentIndex = it.getInt(KEY_CURRENT_INDEX, 0)
        }

        setupListeners()
        updateArtwork(currentIndex)
    }

    private fun setupListeners() {
        previousButton.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                updateArtwork(currentIndex)
            }
        }

        nextButton.setOnClickListener {
            if (currentIndex < ArtworkData.artworks.size - 1) {
                currentIndex++
                updateArtwork(currentIndex)
            }
        }
    }

    private fun updateArtwork(index: Int) {
        val artwork = ArtworkData.artworks[index]

        imageView.setImageResource(artwork.imageResId)
        titleTextView.setText(artwork.titleResId)
        authorTextView.setText(artwork.authorResId)

        updateButtonsState(index)
    }

    private fun updateButtonsState(index: Int) {
        previousButton.isEnabled = index > 0
        nextButton.isEnabled = index < ArtworkData.artworks.size - 1
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_CURRENT_INDEX, currentIndex)
    }

    companion object {
        private const val KEY_CURRENT_INDEX = "current_index"
    }
}