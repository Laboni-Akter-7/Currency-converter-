package com.saadahmedev.currencyconverter.domain.useCase

import android.util.Log
import com.saadahmedev.currencyconverter.domain.model.CurrencyResponse
import com.saadahmedev.currencyconverter.domain.repository.CurrencyConverterRepository
import com.saadahmedev.currencyconverter.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrencyConvertUseCase @Inject constructor(private val currencyConverterRepository: CurrencyConverterRepository) {

    operator fun invoke(
        from: String,
        to: String,
        amount: String
    ): Flow<ResponseState<CurrencyResponse>> = flow {
        emit(ResponseState.Loading())

        Log.d("response_debug", "invoke: $from $to $amount")

        try {
            emit(
                ResponseState.Success(
                    currencyConverterRepository.convertCurrency(
                        fromCurrency = from,
                        toCurrency = to,
                        amount = amount
                    ).toCurrencyResponse()
                )
            )
        } catch (e: Exception) {
            emit(ResponseState.Error(e.message ?: "Unexpected error occurred"))
        }
    }
}