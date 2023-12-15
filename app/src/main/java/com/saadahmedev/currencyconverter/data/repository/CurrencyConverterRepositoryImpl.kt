package com.saadahmedev.currencyconverter.data.repository

import com.saadahmedev.currencyconverter.data.dto.CurrencyConvertResponse
import com.saadahmedev.currencyconverter.data.source.CurrencyApi
import com.saadahmedev.currencyconverter.domain.repository.CurrencyConverterRepository
import javax.inject.Inject

class CurrencyConverterRepositoryImpl @Inject constructor(private val currencyApi: CurrencyApi) :
    CurrencyConverterRepository {

    override suspend fun convertCurrency(
        fromCurrency: String,
        toCurrency: String,
        amount: String
    ): CurrencyConvertResponse =
        currencyApi.convertCurrency(
            amount,
            fromCurrency,
            toCurrency
        )
}