package com.rerumau.proyekti.database

import android.content.SharedPreferences

abstract class DbHelper {

    abstract val editor : SharedPreferences.Editor

    fun clear() {
        editor.clear()
    }
}