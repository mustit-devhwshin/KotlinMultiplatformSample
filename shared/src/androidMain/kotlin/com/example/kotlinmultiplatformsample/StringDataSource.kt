package com.example.kotlinmultiplatformsample

import android.content.SharedPreferences
import androidx.core.content.edit
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual class StringDataSource : KoinComponent {

    private val sharedPreference: SharedPreferences by inject()

    actual fun getSavedString(): String =
        sharedPreference.getString(KEY_STRING, "") ?: ""

    actual fun saveString(string: String) =
        sharedPreference.edit(commit = true) { putString(KEY_STRING, string) }

    companion object {
        private const val KEY_STRING = "KEY_STRING"
    }
}