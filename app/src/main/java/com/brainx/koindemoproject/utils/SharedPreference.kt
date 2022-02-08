package com.brainx.koindemoproject.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(private val context: Context) {

    enum class SpKeys(val key: String) {
        FILE_NAME("KoinDemo"),
        IS_LOGIN("isLogin"),
    }

    private val preferences =
        context.getSharedPreferences(SpKeys.FILE_NAME.key, Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = preferences.edit()

    private fun getSharedPreferenceStringEmptyDefault(key: String):String?{
        return preferences.getString(key, "")
    }

    private fun getSharedPreferenceStringNullDefault(key: String):String?{
        return preferences.getString(key, null)
    }

    private fun getSharedPreferenceInteger(key: String):Int{
        return preferences.getInt(key, -1)
    }

    private fun putSharedPreferenceString(key: String,value:String?){
        editor.apply {
            putString(key, value)
            apply()
        }
    }

    private fun getSharedPreferenceBoolean(key: String):Boolean{
        return preferences.getBoolean(key, false)
    }

    private fun putSharedPreferenceBoolean(key: String,value:Boolean){
        editor.apply {
            putBoolean(key, value)
            apply()
        }
    }

    private fun putSharedPreferenceInt(key: String,value:Int){
        editor.apply {
            putInt(key, value)
            apply()
        }
    }

    fun clearPrefs( cleared: (Boolean) -> Unit) {
        editor.clear().apply()
        cleared(true)
    }

    var isLogin: Boolean
        get() = getSharedPreferenceBoolean(SpKeys.IS_LOGIN.key)
        set(isLogin) = putSharedPreferenceBoolean(SpKeys.IS_LOGIN.key, isLogin)
}