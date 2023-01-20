package com.example.map_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Trail(
    var distance: Double?,
    var fareStage: String?,
    var latitude: Double?,
    var longitude: Double?,
    var name: String?,
    var seq: Int?,
    var time: @RawValue Any?
):Parcelable