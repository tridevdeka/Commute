package com.example.common_utils

import android.app.Activity
import com.example.map_domain.model.LocationsItem

interface Navigator {

    fun navigate(activity: Activity,value: LocationsItem)

    interface Provider{
        fun getActivities(activities: Activities): Navigator
    }
}