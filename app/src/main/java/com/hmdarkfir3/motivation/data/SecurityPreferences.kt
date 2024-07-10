package com.hmdarkfir3.motivation.data

import android.content.Context
import android.content.SharedPreferences
import com.hmdarkfir3.motivation.utils.Constants

class SecurityPreferences(context: Context) {
    private val securityPreferences: SharedPreferences =
        context.getSharedPreferences(Constants.Key.MOTIVATION_STORAGE, Context.MODE_PRIVATE)

    fun storeStringAttribute(key: String, str: String) {
        with(securityPreferences.edit()) {
            putString(key, str)
            apply()
        }
    }

    fun getStringAttributes(key: String): String {
        return securityPreferences.getString(key, "") ?: ""
    }
}