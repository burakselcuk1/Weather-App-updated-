package com.example.weatherapp2.viewmode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp2.model.WeatherModel
import com.example.weatherapp2.service.WeatherAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel: ViewModel() {

    val weatherAPIService = WeatherAPI()
    val disposable = CompositeDisposable()

    val weather_data = MutableLiveData<WeatherModel>()
    val weather_loading = MutableLiveData<Boolean>()
    val weather_error = MutableLiveData<Boolean>()

    fun getDataFromAPI(cName: String){
        weather_loading.value = true
        disposable.addAll(
            weatherAPIService.getWeather(cName).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<WeatherModel>(){
                    override fun onSuccess(t: WeatherModel) {
                        weather_data.value = t
                        weather_error.value = false
                        weather_loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        weather_error.value = true
                        weather_loading.value = false

                    }

                })
        )
    }

}