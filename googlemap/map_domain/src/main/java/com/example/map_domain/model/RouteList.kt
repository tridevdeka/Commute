package com.example.map_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RouteList(
    var routes: List<Route>
):Parcelable
