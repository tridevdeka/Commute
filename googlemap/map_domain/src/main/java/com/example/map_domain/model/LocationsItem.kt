package com.example.map_domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "location_item")

@Parcelize
data class LocationsItem(
    var routeTitle: String?,
    var routeList: RouteList?,
    var totalDistance: Double?,
    var totalDuration: String?,
    var totalFare: Int?,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
) : Parcelable