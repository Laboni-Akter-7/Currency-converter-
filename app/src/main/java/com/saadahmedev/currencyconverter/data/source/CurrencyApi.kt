package com.saadahmedev.currencyconverter.data.source

import com.saadahmedev.currencyconverter.util.AppConstants
import retrofit2.http.GET
import retrofit2.http.Headers

interface CurrencyApi {

    @GET("all")
    @Headers(
        "${AppConstants.Api.X_RAPID_API_HOST_HEADER}: ${AppConstants.Api.X_RAPID_API_HOST}",
        "${AppConstants.Api.X_RAPID_API_KEY_HEADER}: ${AppConstants.Api.X_RAPID_API_KEY}"
    )
    fun getCurrencies()
}