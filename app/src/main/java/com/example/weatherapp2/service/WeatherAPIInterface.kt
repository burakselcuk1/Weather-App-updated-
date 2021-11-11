package com.example.weatherapp2.service

import com.example.weatherapp2.model.WeatherModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPIInterface {
// https://api.openweathermap.org/data/2.5/weather?q=izmir&appid=6c571d5b02d42fc47c67262247d1dee7


    @GET("data/2.5/weather?&units=metric&appid=6c571d5b02d42fc47c67262247d1dee7")

    fun getData(
        @Query("q") cityName: String
    ): Single<WeatherModel>
}