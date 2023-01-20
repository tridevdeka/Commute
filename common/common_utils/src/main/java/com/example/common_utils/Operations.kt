package com.example.common_utils

import com.example.map_domain.model.Route
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import org.json.JSONArray

object Operations {



    fun JSONArray.castToObject(): ArrayList<Route> {
        return Gson().fromJson(
            this.toString(),
            object : TypeToken<List<Route?>?>() {}.type
        )
    }

    fun objectToJSONArray(obj:ArrayList<Route>){
        val jsonArray = Gson().toJsonTree(obj).asJsonArray;

    }

}