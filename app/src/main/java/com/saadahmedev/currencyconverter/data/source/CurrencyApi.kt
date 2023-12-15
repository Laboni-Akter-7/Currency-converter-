package com.saadahmedev.currencyconverter.data.source

import com.saadahmedev.currencyconverter.data.dto.CurrencyConvertResponse
import com.saadahmedev.currencyconverter.util.AppConstants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CurrencyApi {

    @GET("convert")
    @Headers(
        "${AppConstants.Api.X_RAPID_API_HOST_HEADER}: ${AppConstants.Api.X_RAPID_API_HOST}",
        "${AppConstants.Api.X_RAPID_API_KEY_HEADER}: ${AppConstants.Api.X_RAPID_API_KEY}"
    )
    suspend fun convertCurrency(
        @Query("amount") amount: String,
        @Query("from") currencyFrom: String,
        @Query("to") currencyTo: String
    ): CurrencyConvertResponse
}