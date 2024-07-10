package com.hmdarkfir3.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hmdarkfir3.motivation.R
import com.hmdarkfir3.motivation.utils.Constants
import com.hmdarkfir3.motivation.data.SecurityPreferences
import com.hmdarkfir3.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mSecurityPreferences = SecurityPreferences(this)

        setupWindowInsets()
        setupListeners()

        if (isUsernameAlreadyExists()) {
            navigateToMainActivity()
            finish()
        }
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.user) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupListeners() {
        binding.buttonSave.setOnClickListener {
            handleSave()
        }
    }

    private fun handleSave() {
        val username = binding.editUsername.text.toString().trim()

        if (username.isEmpty()) {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
            return
        }

        mSecurityPreferences.storeStringAttribute(Constants.Key.USERNAME_KEY, username)
        navigateToMainActivity()
        finish()
    }

    private fun isUsernameAlreadyExists(): Boolean {
        val username = mSecurityPreferences.getStringAttributes(Constants.Key.USERNAME_KEY)
        return username.isNotEmpty()
    }

    private fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}