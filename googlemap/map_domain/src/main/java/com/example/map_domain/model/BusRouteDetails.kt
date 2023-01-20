package com.example.map_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class BusRouteDetails(
    var routeDescription: String?,
    var routeId: @RawValue Any?,
    var routeName: String?,
    var routeNumber: String?
):Parcelable