package com.hmdarkfir3.motivation.ui

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hmdarkfir3.motivation.R
import com.hmdarkfir3.motivation.data.PhraseMock
import com.hmdarkfir3.motivation.utils.Constants
import com.hmdarkfir3.motivation.data.SecurityPreferences
import com.hmdarkfir3.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWindowInsets()
        setupStatusBar()
        setupListeners()
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupStatusBar() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.purple)
    }

    private fun setupListeners() {
        setUsernameGreeting()
        handleNewPhrase()
        binding.buttonNewPhrase.setOnClickListener {
            handleNewPhrase()
        }
    }

    private fun setUsernameGreeting() {
        val username = SecurityPreferences(this).getStringAttributes(Constants.Key.USERNAME_KEY)
        val helloMessage = getString(R.string.hello, username)
        binding.textHello.text = helloMessage
    }

    private fun handleNewPhrase() {
        val selectedCategoryIndex = binding.tabMotivationCategory.selectedTabPosition
        val newPhrase = PhraseMock().getPhrase(selectedCategoryIndex)
        binding.textPhrase.text = newPhrase
    }
}