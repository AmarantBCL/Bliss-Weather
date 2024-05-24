package com.amarant.apps.blissweather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amarant.apps.blissweather.databinding.ForecastViewholderBinding
import com.amarant.apps.blissweather.model.ForecastResponseApi
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Calendar

class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    var forecasts = listOf<ForecastResponseApi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding =
            ForecastViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val date =
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(differ.currentList[position].dtTxt.toString())
        val calendar = Calendar.getInstance()
        calendar.time = date

        val dayOfWeekName = when (calendar.get(Calendar.DAY_OF_WEEK)) {
            1 -> "Sun"
            2 -> "Mon"
            3 -> "Tue"
            4 -> "Wed"
            5 -> "Thu"
            6 -> "Fri"
            7 -> "Sat"
            else -> "-"
        }
        holder.binding.nameDayTxt.text = dayOfWeekName
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val amPm = if (hour < 12) "am" else "pm"
        val hour12 = calendar.get(Calendar.HOUR)
        holder.binding.hourTxt.text = hour12.toString() + amPm
        holder.binding.tempTxt.text =
            differ.currentList[position].main?.temp?.let { Math.round(it) }.toString() + "°"

        val icon = when(differ.currentList[position].weather?.get(0)?.icon.toString()) {
            "01d", "0n" -> "sunny"
            "02d", "02n" -> "cloudy_sunny"
            "03d", "03n" -> "cloudy_sunny"
            "04d", "04n" -> "cloudy"
            "09d", "09n" -> "rainy"
            "10d", "10n" -> "rainy"
            "11d", "11n" -> "storm"
            "13d", "13n" -> "snowy"
            "50d", "50n" -> "windy"
            else -> "sunny"
        }

        val drawableResourceId: Int = holder.binding.root.resources.getIdentifier(
            icon,
            "drawable",
            holder.binding.root.context.packageName
        )

        Glide.with(holder.binding.root.context)
            .load(drawableResourceId)
            .into(holder.binding.pic)
    }

    override fun getItemCount() = differ.currentList.size

    inner class ForecastViewHolder(val binding: ForecastViewholderBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<ForecastResponseApi.Data>() {
        override fun areItemsTheSame(
            oldItem: ForecastResponseApi.Data,
            newItem: ForecastResponseApi.Data
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ForecastResponseApi.Data,
            newItem: ForecastResponseApi.Data
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}