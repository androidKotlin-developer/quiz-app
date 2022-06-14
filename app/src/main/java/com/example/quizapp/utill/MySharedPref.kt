package com.example.quizapp.utill

import android.content.Context
import android.content.SharedPreferences

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.Serializable
import java.lang.reflect.Type


class MySharedPref(private val DATABASE: String, private val MODE: Int, context: Context) {
    private val context: Context
    fun putString(key: String?, value: String?) {
        val prefs: SharedPreferences.Editor = context.getSharedPreferences(DATABASE, MODE).edit()
        prefs.putString(key, value)
        prefs.apply()
    }

    fun putInt(key: String?, value: Int) {
        val prefs: SharedPreferences.Editor = context.getSharedPreferences(DATABASE, MODE).edit()
        prefs.putInt(key, value)
        prefs.apply()
    }

    fun putBoolean(key: String?, value: Boolean) {
        val prefs: SharedPreferences.Editor = context.getSharedPreferences(DATABASE, MODE).edit()
        prefs.putBoolean(key, value)
        prefs.apply()
    }

    fun putFloat(key: String?, value: Float?) {
        val prefs: SharedPreferences.Editor = context.getSharedPreferences(DATABASE, MODE).edit()
        prefs.putFloat(key, value!!)
        prefs.apply()
    }

    fun putLong(key: String?, value: Long?) {
        val prefs: SharedPreferences.Editor = context.getSharedPreferences(DATABASE, MODE).edit()
        prefs.putLong(key, value!!)
        prefs.apply()
    }

    fun putStringSet(key: String?, value: Set<String?>?) {
        val prefs: SharedPreferences.Editor = context.getSharedPreferences(DATABASE, MODE).edit()
        prefs.putStringSet(key, value)
        prefs.apply()
    }

    fun putSerializable(key: String?, value: Serializable?) {
        val prefs: SharedPreferences.Editor = context.getSharedPreferences(DATABASE, MODE).edit()
        val gson = Gson()
        val json = gson.toJson(value)
        prefs.putString(key, json)
        prefs.apply()
    }

    fun <E> putArrayList(key: String?, list: ArrayList<E>?) {
        val prefs: SharedPreferences.Editor = context.getSharedPreferences(DATABASE, MODE).edit()
        val gson = Gson()
        val json = gson.toJson(list)
        prefs.putString(key, json)
        prefs.apply()
    }
    fun <E> putList(key: String?, list: List<E>?) {
        val prefs: SharedPreferences.Editor = context.getSharedPreferences(DATABASE, MODE).edit()
        val gson = Gson()
        val json = gson.toJson(list)
        prefs.putString(key, json)
        prefs.apply()
    }

    fun getString(key: String?, defValue: String?): String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(DATABASE, MODE)
        return sharedPreferences.getString(key, defValue)
    }

    fun getInt(key: String?, defValue: Int): Int {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(DATABASE, MODE)
        return sharedPreferences.getInt(key, defValue)
    }

    fun getBoolean(key: String?, defValue: Boolean): Boolean {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(DATABASE, MODE)
        return sharedPreferences.getBoolean(key, defValue)
    }

    fun getFloat(key: String?, defValue: Float?): Float {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(DATABASE, MODE)
        return sharedPreferences.getFloat(key, defValue!!)
    }

    fun getLong(key: String?, defValue: Long?): Long {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(DATABASE, MODE)
        return sharedPreferences.getLong(key, defValue!!)
    }

    fun getStringSet(key: String?, defValue: Set<String?>?): Set<String>? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(DATABASE, MODE)
        return sharedPreferences.getStringSet(key, defValue)
    }

    fun <F> getSerializable(key: String?, defValue: String?, classOfT: Class<F>?): F {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(DATABASE, MODE)
        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()
        val json = sharedPreferences.getString(key, defValue)
        return gson.fromJson(json, classOfT)
    }

    fun <E> getArrayList(key: String?, defValue: String?, type: Type?) : ArrayList<E> {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(DATABASE, MODE)
        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()
        val json = sharedPreferences.getString(key, defValue)
        return gson.fromJson(json, type)
    }
    fun <E> getList(key: String?, defValue: String?, type: Type?) : List<E> {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(DATABASE, MODE)
        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()
        val json = sharedPreferences.getString(key, defValue)
        return gson.fromJson(json, type)
    }

    fun deleteExtra(key: String?) {
        val prefs: SharedPreferences.Editor = context.getSharedPreferences(DATABASE, MODE).edit()
        prefs.remove(key)
        prefs.apply()
    }

    init {
        this.context = context
    }
}
