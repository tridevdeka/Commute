package com.example.map_data.local

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.common_utils.Operations.castToObject
import com.example.map_data.R
import com.example.map_domain.model.LocationsItem
import com.example.map_domain.model.RouteList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.io.BufferedReader
import javax.inject.Provider

class LocationCallback(private val context: Context,
private val provider:Provider<LocationDao>) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(IO).launch {
            getJsonData(context)
        }
    }

    private suspend fun getJsonData(context: Context) {
        val jsonArray = loadJSONArray(context)

        for (i in 0 until jsonArray.length()) {
            val routes = jsonArray.getJSONObject(i).getJSONArray("routes")
            val routeTitle = jsonArray.getJSONObject(i).getString("routeTitle")
            val totalDistance = jsonArray.getJSONObject(i).getString("totalDistance")
            val totalDuration = jsonArray.getJSONObject(i).getString("totalDuration")
            val totalFare = jsonArray.getJSONObject(i).getString("totalFare")

            val locationsItem = LocationsItem(
                routeTitle,
                RouteList(routes.castToObject()),
                totalDistance.toDouble(),
                totalDuration,
                totalFare.toInt()
            )

            provider.get().insertLocations(locationsItem)
        }

    }

    private fun loadJSONArray(context: Context): JSONArray {
        val inputStream = context.resources.openRawResource(R.raw.locations)
        val jsonString = BufferedReader(inputStream.reader()).use {
            it.readText()
        }
        return JSONArray(jsonString)
    }

    override fun onOpen(db: SupportSQLiteDatabase) {
        super.onOpen(db)
    }

    override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
        super.onDestructiveMigration(db)
    }


}