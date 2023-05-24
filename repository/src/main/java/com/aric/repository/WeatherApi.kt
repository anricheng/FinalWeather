package com.aric.repository

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/v3/weather/weatherInfo")
   suspend fun getWeatherByCityCode(@Query("city") city:String,@Query("key") key:String ="d796b093b4002891a39cf716baf207d5" ,@Query("extensions") extensions:String ="all" ): AllWeatherResponse
}