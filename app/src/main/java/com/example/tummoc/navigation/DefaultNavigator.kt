package com.example.tummoc.navigation

import com.example.common_utils.Activities
import com.example.common_utils.Navigator
import com.example.tummoc.ui.GoToHomeActivity

class DefaultNavigator: Navigator.Provider {
    override fun getActivities(activities: Activities): Navigator {
        return when(activities){
            is Activities.HomeActivity->{
                GoToHomeActivity
            }
            else->{GoToHomeActivity}
        }
    }

}