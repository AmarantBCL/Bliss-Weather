package com.amarant.apps.blissweather.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amarant.apps.blissweather.activity.MainActivity
import com.amarant.apps.blissweather.databinding.CityViewholderBinding
import com.amarant.apps.blissweather.model.CityResponseApi

class CityAdapter : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding =
            CityViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.binding.cityTxt.text = differ.currentList[position].name
        holder.binding.root.setOnClickListener {
            val intent = Intent(holder.binding.root.context, MainActivity::class.java)
            intent.putExtra("lat", differ.currentList[position].lat)
            intent.putExtra("lon", differ.currentList[position].lon)
            intent.putExtra("name", differ.currentList[position].name)
            holder.binding.root.context.startActivity(intent)
        }
    }

    override fun getItemCount() = differ.currentList.size

    inner class CityViewHolder(val binding: CityViewholderBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<CityResponseApi.CityResponseApiItem>() {
        override fun areItemsTheSame(
            oldItem: CityResponseApi.CityResponseApiItem,
            newItem: CityResponseApi.CityResponseApiItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CityResponseApi.CityResponseApiItem,
            newItem: CityResponseApi.CityResponseApiItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}