package com.saadahmedev.currencyconverter.listener

import com.saadahmedev.currencyconverter.data.dto.CurrencyDto
import com.saadahmedev.currencyconverter.ui.root.tabs.home.util.CurrencyChooserType

interface OnItemClickListener {

    fun onClicked(item: CurrencyDto, position: Int, chooserType: CurrencyChooserType = CurrencyChooserType.FROM)
}