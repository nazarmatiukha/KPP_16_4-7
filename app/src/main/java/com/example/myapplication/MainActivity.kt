package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editTextCity.setText("Київ - столиця України, яка славиться своєю історією та культурою.")

        setupEditTextListeners()

        binding.buttonHide.setOnClickListener {
            binding.textViewInfo.visibility = View.GONE
        }

        binding.buttonShow.setOnClickListener {
            binding.textViewInfo.visibility = View.VISIBLE
        }
    }

    private fun setupEditTextListeners() {
        binding.editTextName.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.editTextName.setTextColor(getColor(R.color.text))
            } else {
                binding.editTextName.setTextColor(getColor(R.color.stale_text))
            }
        }

        binding.editTextGroup.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.editTextGroup.setTextColor(getColor(R.color.text))
            } else {
                binding.editTextGroup.setTextColor(getColor(R.color.stale_text))
            }
        }

        binding.editTextCity.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.editTextCity.setTextColor(getColor(R.color.text))
            } else {
                binding.editTextCity.setTextColor(getColor(R.color.stale_text))
            }
        }
    }
}
