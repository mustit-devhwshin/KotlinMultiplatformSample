package com.example.kotlinmultiplatformsample

import platform.Foundation.NSUserDefaults

actual class StringDataSource {

    actual fun getSavedString(): String =
        NSUserDefaults.standardUserDefaults.stringForKey(KEY_STRING) ?: ""

    actual fun saveString(string: String) {
        NSUserDefaults.standardUserDefaults.setObject(string, KEY_STRING)
    }

    companion object {
        private const val KEY_STRING = "KEY_STRING"
    }
}