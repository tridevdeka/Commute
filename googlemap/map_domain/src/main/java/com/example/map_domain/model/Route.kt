package com.example.map_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Route(
    var busRouteDetails: BusRouteDetails? = null,
    var destinationLat: Double?,
    var destinationLong: Double?,
    var destinationTime: List<String>?,
    var destinationTitle: String?,
    var distance: Double?,
    var duration: String?,
    var fare: Int?,
    var medium: String?,
    var rideEstimation: @RawValue Any? = null,
    var routeName: String?,
    var sourceLat: Double?,
    var sourceLong: Double?,
    var sourceTime: List<String>?,
    var sourceTitle: String?,
    var trails: List<Trail>? = null,
) : Parcelable