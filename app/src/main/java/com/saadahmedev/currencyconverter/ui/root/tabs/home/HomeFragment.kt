package com.saadahmedev.currencyconverter.ui.root.tabs.home

import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.saadahmedev.currencyconverter.base.BaseFragment
import com.saadahmedev.currencyconverter.data.dto.CurrencyDto
import com.saadahmedev.currencyconverter.databinding.FragmentHomeBinding
import com.saadahmedev.currencyconverter.helper.clickable
import com.saadahmedev.currencyconverter.helper.observe
import com.saadahmedev.currencyconverter.listener.OnItemClickListener
import com.saadahmedev.currencyconverter.ui.root.tabs.home.listener.ButtonClickListener
import com.saadahmedev.currencyconverter.ui.root.tabs.home.util.CurrencyBottomSheetDialog
import com.saadahmedev.currencyconverter.ui.root.tabs.home.util.CurrencyChooserType
import com.saadahmedev.currencyconverter.ui.root.tabs.home.viewmodel.HomeFragmentViewModel
import com.saadahmedev.currencyconverter.util.AppConstants.AppInfo.APP_NAME
import com.saadahmedev.currencyconverter.util.ProgressDialog
import com.saadahmedev.currencyconverter.util.ResponseState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    ButtonClickListener, OnItemClickListener {
    override val title: String
        get() = APP_NAME
    override val isBackButtonVisible: Boolean
        get() = false

    private val viewModel by viewModels<HomeFragmentViewModel>()
    private lateinit var currencyBottomSheetDialog: CurrencyBottomSheetDialog
    private lateinit var progressDialog: ProgressDialog

    override fun onFragmentCreate(savedInstanceState: Bundle?) {
        viewModel.start()
        binding.listener = this
        binding.viewmodel = viewModel
        progressDialog = ProgressDialog.getInstance(requireContext())
        binding.btnConvert.clickable { false }

        currencyBottomSheetDialog = CurrencyBottomSheetDialog.getInstance(requireContext(), this)
            .build(viewModel.currencyList)

        binding.etAmount.doAfterTextChanged {
            binding.btnConvert.clickable {
                !viewModel.amount.get()
                    .isNullOrBlank() && viewModel.fromCode.get() != viewModel.toCode.get()
            }
        }
    }

    override fun observeData() {
        observe(viewModel.convertResponse) {
            when (it) {
                is ResponseState.Loading -> {
                    viewModel.isResultView.set(false)
                    progressDialog.show("Converting ${viewModel.amount.get()} ${viewModel.fromCode.get()} to ${viewModel.toCode.get()}")
                }

                is ResponseState.Success -> {
                    binding.item = it.data.apply {
                        this?.from = "${viewModel.currencyMap[this?.from]} (${this?.from})"
                        this?.to = "${viewModel.currencyMap[this?.to]} (${this?.to})"
                    }
                    progressDialog.dismiss()
                    viewModel.isResultView.set(true)
                }

                is ResponseState.Error -> {
                    progressDialog.dismiss()
                    viewModel.isResultView.set(false)
                    it.message.shortSnackBar()
                }
            }
        }
    }

    override fun onCurrencyClicked(chooserType: CurrencyChooserType) {
        currencyBottomSheetDialog.show(chooserType)
    }

    override fun onConvertClicked() {
        viewModel.convert()
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

        binding.btnConvert.clickable {
            !viewModel.amount.get()
                .isNullOrBlank() && viewModel.fromCode.get() != viewModel.toCode.get()
        }
    }
}