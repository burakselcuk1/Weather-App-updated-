package com.example.weatherapp2.service

import com.example.weatherapp2.model.WeatherModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherAPI {
    // https://api.openweathermap.org/data/2.5/weather?q=izmir&appid=6c571d5b02d42fc47c67262247d1dee7


    private val BASE_URL ="https://api.openweathermap.org/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(WeatherAPIInterface::class.java)

    fun getWeather(cName: String):Single<WeatherModel>{
        return api.getData(cName)
    }

}