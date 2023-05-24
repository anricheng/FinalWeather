package com.aric.finalweather

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aric.repository.AllWeatherResponse

class WeatherListAdapter :
    ListAdapter<AllWeatherResponse, WeatherListAdapter.ViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<AllWeatherResponse>() {
            override fun areItemsTheSame(
                oldItem: AllWeatherResponse,
                newItem: AllWeatherResponse
            ): Boolean {
                return oldItem.forecasts[0].city == newItem.forecasts[0].city
            }

            override fun areContentsTheSame(
                oldItem: AllWeatherResponse,
                newItem: AllWeatherResponse
            ): Boolean {

                return oldItem.forecasts[0].city == newItem.forecasts[0].city
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var dateValue: TextView = itemView.findViewById(R.id.date_value)
        var titleValue: TextView = itemView.findViewById(R.id.title)
        var dayValue: TextView = itemView.findViewById(R.id.day_value)
        var nightValue: TextView = itemView.findViewById(R.id.night_value)
        var windValue: TextView = itemView.findViewById(R.id.wind_value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = View.inflate(parent.context, R.layout.item_recyclerview_weather, null)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.titleValue.text = item.forecasts[0].city
        holder.dateValue.text = item.forecasts[0].casts[0].date
        holder.dayValue.text =
            "${item.forecasts[0].casts[0].dayweather}--${item.forecasts[0].casts[0].daytemp}度"
        holder.nightValue.text =
            "${item.forecasts[0].casts[0].nightweather}--${item.forecasts[0].casts[0].nighttemp}度"
        holder.windValue.text =
            "白天：${item.forecasts[0].casts[0].daywind}--晚上:${item.forecasts[0].casts[0].nightwind}"
    }
}