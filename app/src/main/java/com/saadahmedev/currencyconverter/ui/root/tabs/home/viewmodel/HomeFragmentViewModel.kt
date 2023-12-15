package com.saadahmedev.currencyconverter.ui.root.tabs.home.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.saadahmedev.currencyconverter.data.dto.CurrencyDto
import java.util.Currency
import java.util.Locale

class HomeFragmentViewModel : ViewModel() {

    val currencyList = arrayListOf<CurrencyDto>()
    val fromCode = ObservableField("USD")
    val toCode = ObservableField("BDT")

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
            } catch (_: Exception) {}
        }

        currencyList.addAll(currencyUniqueSet.toList())
    }
}