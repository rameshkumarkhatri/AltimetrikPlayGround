package com.mobifyall.altimetrikplayground

import android.content.Context

class SharedData {
    companion object {
        val status= "status"
        private val name = "PREF_ALTI"
        val id = "ID"
        val image = "Image"
        val desc = "desc"
        val fuel = "fuel"
        public fun saveData(context: Context, data: String, key: String) {
            context.getSharedPreferences(name, Context.MODE_PRIVATE).edit().putString(key, data).apply()
        }


        public fun getData(context: Context, key: String): String {
            return context.getSharedPreferences(name, Context.MODE_PRIVATE).getString(key, "");
        }

        fun getPostParameterFuel(fuelType: String): String {
            when (fuelType)
            {
                "Benzin" -> return "fuel"
                 "" -> return "stateofcharge"
            }
            return "stateofcharge"
        }
    }
}