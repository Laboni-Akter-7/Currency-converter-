package com.saadahmedev.currencyconverter.ui.root.tabs.home.adapter

import com.saadahmedev.currencyconverter.R
import com.saadahmedev.currencyconverter.base.BaseRecyclerAdapter
import com.saadahmedev.currencyconverter.data.dto.CurrencyDto
import com.saadahmedev.currencyconverter.databinding.ItemCurrencyBinding
import com.saadahmedev.currencyconverter.helper.onClicked
import com.saadahmedev.currencyconverter.listener.OnItemClickListener

class CurrencyAdapter(private val listener: OnItemClickListener) : BaseRecyclerAdapter<CurrencyDto, ItemCurrencyBinding>() {
    override val layoutRes: Int
        get() = R.layout.item_currency

    override fun onBind(binding: ItemCurrencyBinding, item: CurrencyDto, position: Int) {
        binding.item = item
        binding.root.onClicked {
            listener.onClicked(
                item = item,
                position = position
            )
        }
    }
}