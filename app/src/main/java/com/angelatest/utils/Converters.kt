package com.angelatest.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromProblemsList(value: List<Map<String, List<Map<String, Any>>>>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toProblemsList(value: String): List<Map<String, List<Map<String, Any>>>> {
        val type = object : TypeToken<List<Map<String, List<Map<String, Any>>>>>() {}.type
        return gson.fromJson(value, type)
    }
}