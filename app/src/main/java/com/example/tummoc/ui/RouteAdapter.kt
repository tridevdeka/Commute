package com.example.tummoc.ui

import android.content.Context
import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ImageSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.common_utils.Utils
import com.example.map_domain.model.LocationsItem
import com.example.tummoc.R
import com.example.tummoc.databinding.RouteContainerBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry


class RouteAdapter(private val clickListener: ClickListener) :
    RecyclerView.Adapter<RouteAdapter.RouteViewHolder>() {

    class RouteViewHolder(val mBinding: RouteContainerBinding) :
        RecyclerView.ViewHolder(mBinding.root)


    private val diffCallback = object : DiffUtil.ItemCallback<LocationsItem>() {
        override fun areItemsTheSame(oldItem: LocationsItem, newItem: LocationsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LocationsItem, newItem: LocationsItem): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder {
        return RouteViewHolder(
            RouteContainerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RouteViewHolder, position: Int) {
        val item = differ.currentList[position]
        val distances = ArrayList<Float>()
        val spannableStringBuilder = SpannableStringBuilder(" ")

        item.routeList!!.routes.forEach { route ->
            if (route.medium!!.isNotEmpty()) {
                distances.add((route.distance)!!.toFloat())
                if (route.medium == "Walk") {
                    spannableStringBuilder.setSpan(
                        ImageSpan(holder.mBinding.root.context, R.drawable.ic_walk),
                        spannableStringBuilder.length - 1, spannableStringBuilder.length, 0
                    )
                    spannableStringBuilder.append(Utils.getFormattedDuration(route.duration!!) + " -> ")
                } else if (route.medium == "Bus" && route.routeName != null) {
                    spannableStringBuilder.setSpan(
                        ImageSpan(holder.mBinding.root.context, R.drawable.ic_bus),
                        spannableStringBuilder.length - 1, spannableStringBuilder.length, 0
                    )
                    spannableStringBuilder.append(Utils.getAbbreviated(route.routeName!!) + " -> ")
                }

            }
        }

        holder.mBinding.tvTrack.text = spannableStringBuilder.delete(
            spannableStringBuilder.length - 3,
            spannableStringBuilder.length
        )

        holder.mBinding.barChart.setUpBarChart(distances, holder.mBinding.root.context)
        holder.mBinding.tvEstimatedPrice.text = "₹ ${item.totalFare}"
        holder.mBinding.tvTravelTime.text = "${item.totalDuration} mins"
        holder.mBinding.tvDistance.text = String.format("%.2f", item.totalDistance) + " Kms"

        holder.itemView.setOnClickListener {
            it.animate().scaleX(0.7f).scaleY(0.7f).setDuration(200).withEndAction(Runnable {
                it.animate().scaleX(1f).scaleY(1f).duration = 200
            })
            Handler(Looper.getMainLooper()).postDelayed({
                clickListener.onClick(item)
            }, 400)
        }
    }

    private fun TextView.appendImage(bmp: Bitmap) {
        transformationMethod = null
        val ss = SpannableString("  ")
        ss.setSpan(ImageSpan(bmp, ImageSpan.ALIGN_BASELINE), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        append(ss)
    }


    private fun BarChart.setUpBarChart(distances: ArrayList<Float>, context: Context) {
        this.apply {

            setTouchEnabled(false)

            axisRight.setDrawAxisLine(false)
            axisLeft.setDrawAxisLine(false)
            xAxis.setDrawAxisLine(false)

            axisRight.setDrawGridLines(false)
            axisLeft.setDrawGridLines(false)
            xAxis.setDrawGridLines(false)

            axisLeft.setDrawLabels(false)
            axisRight.setDrawLabels(false)
            xAxis.setDrawLabels(false)

            setDrawBorders(false)
            setDrawGridBackground(false)

            description.isEnabled = false
            legend.isEnabled = false

            val barChartDataSet = BarDataSet(dataValue(distances.toFloatArray()), "Bar set")

            val colors = arrayListOf(
                context.resources.getColor(R.color.walk),
                context.resources.getColor(R.color.bus),
                context.resources.getColor(R.color.walk)
            )
            barChartDataSet.colors = colors
            barChartDataSet.setDrawValues(false)

            val barData = BarData(barChartDataSet)
            barData.barWidth = 1f
            data = barData
        }

    }

    private fun dataValue(distances: FloatArray): ArrayList<BarEntry> {
        val arrayList = ArrayList<BarEntry>()
        arrayList.add(BarEntry(0f, distances))
        return arrayList
    }

    interface ClickListener {
        fun onClick(locationsItem: LocationsItem)
    }
}