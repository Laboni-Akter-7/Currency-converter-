package com.saadahmedev.currencyconverter.ui.root.tabs.home

import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.saadahmedev.currencyconverter.base.BaseFragment
import com.saadahmedev.currencyconverter.data.dto.CurrencyDto
import com.saadahmedev.currencyconverter.databinding.FragmentHomeBinding
import com.saadahmedev.currencyconverter.helper.disable
import com.saadahmedev.currencyconverter.helper.enable
import com.saadahmedev.currencyconverter.listener.OnItemClickListener
import com.saadahmedev.currencyconverter.ui.root.tabs.home.listener.ButtonClickListener
import com.saadahmedev.currencyconverter.ui.root.tabs.home.util.CurrencyBottomSheetDialog
import com.saadahmedev.currencyconverter.ui.root.tabs.home.util.CurrencyChooserType
import com.saadahmedev.currencyconverter.ui.root.tabs.home.viewmodel.HomeFragmentViewModel
import com.saadahmedev.currencyconverter.util.AppConstants.AppInfo.APP_NAME

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    ButtonClickListener, OnItemClickListener {
    override val title: String
        get() = APP_NAME
    override val isBackButtonVisible: Boolean
        get() = false

    private val viewModel by viewModels<HomeFragmentViewModel>()
    private lateinit var currencyBottomSheetDialog: CurrencyBottomSheetDialog

    override fun onFragmentCreate(savedInstanceState: Bundle?) {
        viewModel.start()
        binding.listener = this
        binding.viewmodel = viewModel
        binding.btnConvert.disable()
        currencyBottomSheetDialog = CurrencyBottomSheetDialog.getInstance(requireContext(), this)
            .build(viewModel.currencyList)

        binding.etAmount.doAfterTextChanged {
            if (it.toString().isNotEmpty()) binding.btnConvert.enable()
            else binding.btnConvert.disable()
        }
    }

    override fun observeData() {
        //
    }

    override fun onCurrencyClicked(chooserType: CurrencyChooserType) {
        currencyBottomSheetDialog.show(chooserType)
    }

    override fun onConvertClicked() {
        //
    }

    override fun onExchangeClicked() {
        val tempCode = viewModel.fromCode.get()
        viewModel.fromCode.set(viewModel.toCode.get())
        viewModel.toCode.set(tempCode)
    }

    override fun onClicked(item: CurrencyDto, position: Int, chooserType: CurrencyChooserType) {
        when (chooserType) {
            CurrencyChooserType.FROM -> viewModel.fromCode.set(item.code)
            CurrencyChooserType.TO -> viewModel.toCode.set(item.code)
        }
    }
}