package com.example.drinkproject.RESOURCE

import android.content.Context
import android.content.SharedPreferences

class DrinkSharedPreferences(context : Context) {
    private val PREFS_FILENAME = "prefs"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    /** put Int Value
     * @param key
     * @param value
     */
    fun putIntData(key: String,value: Int) {
        prefs.edit().putInt(key, value).apply()
    }

    /** put Boolean Value
     * @param key
     * @param value
     */
    fun putBooleanData(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    /** put String Vaule
     * @param key
     * @param value
     */
    fun putStringData(key: String, value: String?) {
        prefs.edit().putString(key, value).apply()
    }

    /** get Int Value
     * @param key
     */
    fun getIntData(key: String) : Int{
        return prefs.getInt(key, 0)
    }

    /** get Boolean Value
     * @param key
     */
    fun getBooleanData(key: String) : Boolean {
        return prefs.getBoolean(key, false)
    }

    /** get String Vaule
     * @param key
     */
    fun getStringData(key: String) : String? {
        return prefs.getString(key, "")
    }

    /** put Int Value
     * @param key
     * @param value
     */
    fun putArrayData(key: String,value: Set<String>) {
        prefs.edit().putStringSet(key,value)
    }

    fun getArrayData(key: String) : Set<String> {
        return prefs.getStringSet(key, null) as Set<String>
    }
}