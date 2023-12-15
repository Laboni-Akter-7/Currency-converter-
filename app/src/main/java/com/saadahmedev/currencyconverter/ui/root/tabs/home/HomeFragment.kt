package com.saadahmedev.currencyconverter.ui.root.tabs.home

import android.os.Bundle
import com.saadahmedev.currencyconverter.base.BaseFragment
import com.saadahmedev.currencyconverter.databinding.FragmentHomeBinding
import com.saadahmedev.currencyconverter.util.AppConstants.AppInfo.APP_NAME

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    override val title: String
        get() = APP_NAME
    override val isBackButtonVisible: Boolean
        get() = false

    override fun onFragmentCreate(savedInstanceState: Bundle?) {
        //
    }

    override fun observeData() {
        //
    }
}