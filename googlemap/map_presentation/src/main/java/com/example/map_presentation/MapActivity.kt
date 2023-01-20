package com.example.map_presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.common_utils.Navigator
import com.example.common_utils.Utils
import com.example.map_domain.model.LocationsItem
import com.example.map_presentation.databinding.ActivityMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private lateinit var mBinding: ActivityMapBinding
    private lateinit var locationsItem: LocationsItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        locationsItem = intent.getParcelableExtra("item")!!

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        bottomSheetBehaviour()
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.clear()

        val polylineOptions = PolylineOptions()
        locationsItem.routeList?.routes?.forEach { route ->
            route.trails?.let {
                for (trail in route.trails!!) {
                    mMap.addPolyline(
                        polylineOptions
                            .color(ContextCompat.getColor(this, R.color.lightBlue))
                            .add(LatLng(trail.latitude!!, trail.longitude!!))
                    ).apply {
                        startCap =
                            CustomCap(bitmapFromVector(this@MapActivity, R.drawable.ic_source))
                        endCap = CustomCap(bitmapFromVector(this@MapActivity, R.drawable.ic_star))
                    }
                }

            }
        }


        val routes = locationsItem.routeList?.routes
        val sourceLat = routes?.get(0)?.sourceLat
        val sourceLon = routes?.get(0)?.sourceLong
        val destLat = routes?.get(routes.size - 1)?.destinationLat
        val destLon = routes?.get(routes.size - 1)?.destinationLong

        val lastLocation = LatLng(destLat!!, destLon!!)
        mMap.addMarker(
            MarkerOptions().position(lastLocation)
                .title(routes[routes.size - 1].destinationTitle)
        )
            ?.setIcon(bitmapFromVector(this, R.drawable.ic_dest))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lastLocation))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12f))

    }


    private fun bitmapFromVector(context: Context, vectorResId: Int): BitmapDescriptor {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )
        val bitmap: Bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)

        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }


    private fun bottomSheetBehaviour() {
        BottomSheetBehavior.from(mBinding.flContainer).apply {
            peekHeight = 400
            state = BottomSheetBehavior.STATE_COLLAPSED
            maxHeight = 1100
        }

    }

    override fun onStart() {
        super.onStart()
        clickAnimation()
        initView()
    }

    private fun initView() {
        var count = 0
        locationsItem.routeList?.routes?.forEach {
            count++
            if (count == 1) {
                mBinding.tvSource.text = it.sourceTitle
                val distance = " -> ${Utils.getFormattedDuration(it.duration!!)} -> "
                mBinding.tvWalkDistance.text =
                    distance + String.format("%.2f", it.distance?.times(1000)) + " Mtrs"
            }

            if (count == 2) {
                mBinding.tvGetInStation.text = it.sourceTitle
                val distance = " -> ${Utils.getFormattedDuration(it.duration!!)} -> "
                mBinding.tvGetInDuration.text =
                    distance + String.format("%.2f", it.distance) + " Kms"
            }

            if (count == 3)
                mBinding.tvGetDownStation.text = it.sourceTitle
            mBinding.tvDestination.text = it.destinationTitle

        }

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun clickAnimation() {
        mBinding.mcvContainer.setOnClickListener {
            it.animate().scaleX(0.7f).scaleY(0.7f).setDuration(300).withEndAction(Runnable {
                it.animate().scaleX(1f).scaleY(1f).duration = 300
            })
        }
        mBinding.mcvContainerGetIn.setOnClickListener {
            it.animate().scaleX(0.7f).scaleY(0.7f).setDuration(300).withEndAction(Runnable {
                it.animate().scaleX(1f).scaleY(1f).duration = 300
            })
        }
        mBinding.mvcDestinationContainer.setOnClickListener {
            it.animate().scaleX(0.7f).scaleY(0.7f).setDuration(300).withEndAction(Runnable {
                it.animate().scaleX(1f).scaleY(1f).duration = 300
            })
        }


        val scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up)
        val scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down)

        /*mBinding.mcvContainer.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_UP -> {
                    mBinding.mcvContainer.startAnimation(scaleDown)
                }

                MotionEvent.ACTION_DOWN -> {
                    mBinding.mcvContainer.startAnimation(scaleUp)
                }
            }

            v?.onTouchEvent(event) ?: true
        }*/

    }

    companion object {
        fun launchActivity(activity: Activity) {
            Intent(activity, MapActivity::class.java).also {
                activity.startActivity(it)
            }
        }
    }


}
