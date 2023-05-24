package com.aric.finalweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aric.repository.AllWeatherResponse
import com.aric.repository.ServiceProvider
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivityViewModel : ViewModel() {

    private val _weatherList: MutableLiveData<List<AllWeatherResponse>> = MutableLiveData()
    val weatherList: LiveData<List<AllWeatherResponse>> = _weatherList

    fun getWeatherList(cityList: List<String>) {
        viewModelScope.launch {
            val list: MutableList<Deferred<AllWeatherResponse>> = mutableListOf()
            try {
                coroutineScope {
                    cityList.forEach {
                        list.add(async {
                            ServiceProvider.getWeatherApi().getWeatherByCityCode(it)
                        })
                    }
                    _weatherList.value = list.awaitAll()
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}