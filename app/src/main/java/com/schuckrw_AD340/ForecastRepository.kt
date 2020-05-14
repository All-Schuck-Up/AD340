package com.schuckrw_AD340

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class ForecastRepository {

    private val _weeklyforecast = MutableLiveData<List<DailyForecast>>()
    val weeklyForecast: LiveData<List<DailyForecast>> = _weeklyforecast

    fun loadForecast(zipcode: String) {
        val randomValues = List(10) { Random.nextFloat().rem(100) * 100 }
        val forecastItems = randomValues.map { temp ->
            DailyForecast(temp, getTempDescription(temp))
        }
        _weeklyforecast.setValue(forecastItems)
    }

    private fun getTempDescription(temp: Float) : String {
        return when (temp) {
            in Float.MIN_VALUE.rangeTo(0f) -> "Anything below 0 doesn't make sense"
            in 0f.rangeTo(32f) -> "I would stay by the fire!"
            in 32f.rangeTo(55f) -> "Bundle up, it's chilly"
            in 55f.rangeTo(65f) -> "This is an improvement"
            in 65f.rangeTo(80f) -> "Get that boat out on the water!"
            in 80f.rangeTo(90f) -> "Dig out the A/C"
            in 90f.rangeTo(Float.MAX_VALUE) -> "Look at the sun from an cooled room"
            else -> "Does not compute"
        }
    }

}