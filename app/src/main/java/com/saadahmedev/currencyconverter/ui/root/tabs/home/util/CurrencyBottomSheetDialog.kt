package com.saadahmedev.currencyconverter.ui.root.tabs.home.util

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.saadahmedev.currencyconverter.R
import com.saadahmedev.currencyconverter.data.dto.CurrencyDto
import com.saadahmedev.currencyconverter.databinding.DialogCurrencyChooserBinding
import com.saadahmedev.currencyconverter.helper.onClicked
import com.saadahmedev.currencyconverter.helper.setLinearLayoutManager
import com.saadahmedev.currencyconverter.listener.OnItemClickListener
import com.saadahmedev.currencyconverter.ui.root.tabs.home.adapter.CurrencyAdapter

class CurrencyBottomSheetDialog private constructor(
    private val context: Context,
    private val listener: OnItemClickListener
) : OnItemClickListener {

    private lateinit var binding: DialogCurrencyChooserBinding
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var chooserType: CurrencyChooserType
    private lateinit var recyclerView: RecyclerView
    private lateinit var currencyList: List<CurrencyDto>

    private val adapter by lazy {
        CurrencyAdapter(this)
    }

    companion object {

        fun getInstance(
            context: Context,
            listener: OnItemClickListener
        ): CurrencyBottomSheetDialog = CurrencyBottomSheetDialog(context, listener)
    }

    fun build(currencyList: List<CurrencyDto>): CurrencyBottomSheetDialog {
        bottomSheetDialog = BottomSheetDialog(context)
        this.currencyList = currencyList

        binding = DataBindingUtil.inflate<DialogCurrencyChooserBinding>(
            LayoutInflater.from(context),
            R.layout.dialog_currency_chooser,
            null,
            false
        )
        bottomSheetDialog.setContentView(binding.root)
        recyclerView = binding.recyclerView

        binding.btnClose.onClicked { dismiss() }
        this.recyclerView.setLinearLayoutManager(context)
        this.recyclerView.adapter = adapter
        adapter.addItems(this.currencyList)

        binding.etSearch.doAfterTextChanged {
            doFilter(it.toString().lowercase(), currencyList)
        }

        return this
    }

    fun show(type: CurrencyChooserType) {
        if (!bottomSheetDialog.isShowing) {
            this.chooserType = type
            bottomSheetDialog.show()
        }
    }

    private fun dismiss() {
        if (bottomSheetDialog.isShowing) {
            binding.etSearch.text = null
            bottomSheetDialog.dismiss()
        }
    }

    private fun doFilter(text: String, items: List<CurrencyDto>) {
        val filteredItems =
            items.filter { it.code.lowercase().startsWith(text) || it.name.lowercase().startsWith(text) }
        adapter.addItems(filteredItems)
    }

    override fun onClicked(item: CurrencyDto, position: Int, chooserType: CurrencyChooserType) {
        dismiss()
        listener.onClicked(
            item = item,
            position = position,
            chooserType = this.chooserType
        )
    }
}