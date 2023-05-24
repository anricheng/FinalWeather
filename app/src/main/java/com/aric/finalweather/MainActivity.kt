package com.aric.finalweather

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aric.repository.ServiceProvider
import com.pluto.plugins.network.PlutoInterceptor
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var weatherList: RecyclerView
    private lateinit var weatherListAdapter: WeatherListAdapter
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        weatherListAdapter = WeatherListAdapter()
        weatherList = findViewById<RecyclerView>(R.id.weatherList).apply {
            adapter = weatherListAdapter
        }
        viewModel.getWeatherList(listOf("110000", "310000", "440100", "440300", "320500", "210100"))
        viewModel.weatherList.observe(this) {
            weatherListAdapter.submitList(it)
        }
    }
}

