package com.saadahmedev.currencyconverter.ui.root.tabs.home.listener

import com.saadahmedev.currencyconverter.ui.root.tabs.home.util.CurrencyChooserType

interface ButtonClickListener {

    fun onCurrencyClicked(chooserType: CurrencyChooserType)

    fun onConvertClicked()

    fun onExchangeClicked()
}