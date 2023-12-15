package com.saadahmedev.currencyconverter.domain.model

data class CurrencyResponse(
    var from: String,
    var to: String,
    val result: String,
    val rate: String
)
