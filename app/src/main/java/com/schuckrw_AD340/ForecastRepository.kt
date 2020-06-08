package com.schuckrw_AD340

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.Date
import kotlin.random.Random

class ForecastRepository {

    private val _weeklyForecast = MutableLiveData<List<DailyForecast>>()
    val weeklyForecast: LiveData<List<DailyForecast>> = _weeklyForecast

    fun loadForecast(zipcode: String) {
        val randomValues = List(7) { Random.nextFloat().rem(100) * 100 }
        val forecastItems = randomValues.map {
            DailyForecast(Date(), it, getTempDescription(it))
        }
        _weeklyForecast.setValue(forecastItems)
    }

    private fun getTempDescription(temp: Float) : String {
        return when (temp) {
            in Float.MIN_VALUE.rangeTo(0f) -> "Anything below 0 doesn't make sense"
            in 0f.rangeTo(32f) -> "Stay close to the fire"
            in 32f.rangeTo(55f) -> "Still to cold"
            in 55f.rangeTo(65f) -> "Improving"
            in 65f.rangeTo(80f) -> "Perfection!"
            in 80f.rangeTo(90f) -> "Getting a little warm"
            in 90f.rangeTo(100f) -> "Where's the A/C?"
            in 100f.rangeTo(Float.MAX_VALUE) -> "Nope, just stay inside?"
            else -> "Does not compute"
        }
    }
}