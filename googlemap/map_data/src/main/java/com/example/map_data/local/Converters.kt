package com.example.map_data.local

import android.util.Log
import androidx.room.TypeConverter
import com.example.map_domain.model.RouteList
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun toRouteList(value: String): RouteList {
        Log.d("DBINFO_FROMJSON", "Extracted>>${value}")
        return Gson().fromJson(value, RouteList::class.java)
    }

    @TypeConverter
    fun fromRouteList(value: RouteList): String {
        return Gson().toJson(value)
    }
}