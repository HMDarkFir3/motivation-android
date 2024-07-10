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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(binding.main.id)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.purple)

        getUsernameOfSharedPreferences()
        handleNewPhrase()

        binding.buttonNewPhrase.setOnClickListener {
            handleNewPhrase()
        }
    }

    private fun getUsernameOfSharedPreferences() {
        val username = SecurityPreferences(this).getStringAttributes(Constants.KEY.USERNAME_KEY)
        val helloMessage = getString(R.string.hello, username)
        binding.textHello.text = helloMessage
    }

    private fun handleNewPhrase() {
        val selectedCategoryIndex = binding.tabMotivationCategory.selectedTabPosition
        val newPhrase = PhraseMock().getPhrase(selectedCategoryIndex)
        binding.textPhrase.text = newPhrase
    }
}