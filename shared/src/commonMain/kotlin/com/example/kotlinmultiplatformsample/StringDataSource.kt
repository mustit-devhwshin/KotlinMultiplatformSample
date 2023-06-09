package com.example.kotlinmultiplatformsample

expect class StringDataSource() {

    fun getSavedString(): String

    fun saveString(string: String)
}
