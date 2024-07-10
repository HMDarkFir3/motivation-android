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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(binding.user.id)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mSecurityPreferences = SecurityPreferences(this)

        verifyUsernameAlreadyExists()

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

        mSecurityPreferences.storeStringAttribute(Constants.KEY.USERNAME_KEY, username)

        startActivity(navigateToActivity(MainActivity::class.java))
        finish()
    }

    private fun verifyUsernameAlreadyExists() {
        val username = mSecurityPreferences.getStringAttributes(Constants.KEY.USERNAME_KEY)

        if (username.isNotEmpty()) {
            startActivity(navigateToActivity(MainActivity::class.java))
            finish()
        }
    }

    private fun <T> navigateToActivity(clazz: Class<T>): Intent {
        return Intent(this, clazz)
    }
}