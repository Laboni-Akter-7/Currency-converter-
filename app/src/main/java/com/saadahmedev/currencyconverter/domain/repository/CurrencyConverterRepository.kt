package com.saadahmedev.currencyconverter.domain.repository

import com.saadahmedev.currencyconverter.data.dto.CurrencyConvertResponse

interface CurrencyConverterRepository {

    suspend fun convertCurrency(
        fromCurrency: String,
        toCurrency: String,
        amount: String
    ): CurrencyConvertResponse
}