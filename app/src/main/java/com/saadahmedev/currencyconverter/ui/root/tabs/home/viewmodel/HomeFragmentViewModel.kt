package com.saadahmedev.currencyconverter.ui.root.tabs.home.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saadahmedev.currencyconverter.data.dto.CurrencyDto
import com.saadahmedev.currencyconverter.domain.model.CurrencyResponse
import com.saadahmedev.currencyconverter.domain.useCase.CurrencyConvertUseCase
import com.saadahmedev.currencyconverter.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Currency
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(private val currencyConvertUseCase: CurrencyConvertUseCase) : ViewModel() {

    val currencyList = arrayListOf<CurrencyDto>()
    val currencyMap = hashMapOf<String, String>()

    val fromCode = ObservableField("USD")
    val toCode = ObservableField("BDT")
    val amount = ObservableField<String>()
    val isResultView = ObservableBoolean(false)

    private val _convertResponse = MutableLiveData<ResponseState<CurrencyResponse>>()
    val convertResponse: LiveData<ResponseState<CurrencyResponse>>
        get() = _convertResponse

    fun start() {
        populateCurrencyList()
    }

    private fun populateCurrencyList() {
        val currencyUniqueSet = hashSetOf<CurrencyDto>()
        Locale.getAvailableLocales().forEach {
            try {
                val currency = Currency.getInstance(it)
                currencyUniqueSet.add(
                    CurrencyDto(
                        code = currency.currencyCode,
                        name = currency.displayName
                    )
                )
                currencyMap[currency.currencyCode] = currency.displayName
            } catch (_: Exception) {}
        }

        currencyList.addAll(currencyUniqueSet.toList())
    }

    fun convert() {
        viewModelScope.launch(Dispatchers.IO) {
            currencyConvertUseCase.invoke(
                fromCode.get() ?: "",
                toCode.get()?: "",
                amount.get() ?: "0.0"
            ).collect {
                _convertResponse.postValue(it)
            }
        }
    }
}