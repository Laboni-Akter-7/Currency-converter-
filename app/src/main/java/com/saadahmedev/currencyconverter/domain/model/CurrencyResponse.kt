package com.saadahmedev.currencyconverter.domain.model

data class CurrencyResponse(
    val from: String,
    val to: String,
    val result: String,
    val rate: String
)
