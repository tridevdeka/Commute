package com.example.tummoc.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common_utils.Activities
import com.example.common_utils.Navigator
import com.example.map_domain.model.LocationsItem
import com.example.map_presentation.MapActivity
import com.example.tummoc.R
import com.example.tummoc.databinding.ActivityHomeBinding
import com.example.tummoc.viewmodel.MapViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), RouteAdapter.ClickListener {

    private lateinit var mBinding: ActivityHomeBinding

    private val viewModel: MapViewModel by viewModels()

    @Inject
    lateinit var provider: Navigator.Provider

    private val routeAdapter by lazy {
        RouteAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)


        viewModel.getAllLocations().observe(this, Observer {
            setUpRecyclerView()
            routeAdapter.differ.submitList(it)
        })


    }

    private fun setUpRecyclerView() {
        val animationController: LayoutAnimationController =
            AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation)
        mBinding.rvRoutes.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            layoutAnimation = animationController
            adapter = routeAdapter
        }
    }

    override fun onClick(locationsItem: LocationsItem) {
        provider.getActivities(Activities.MapActivity).navigate(this@HomeActivity, locationsItem)
    }


    companion object {
        fun launchActivity(activity: Activity, item: LocationsItem) {
            Intent(activity, MapActivity::class.java).also {
                it.putExtra("item", item)
                activity.startActivity(it)
            }
        }
    }

}

object GoToHomeActivity : Navigator {
    override fun navigate(activity: Activity, value: LocationsItem) {
        HomeActivity.launchActivity(activity, value)
    }
}





