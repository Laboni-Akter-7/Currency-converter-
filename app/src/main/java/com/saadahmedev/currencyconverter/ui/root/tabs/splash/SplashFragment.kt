package com.saadahmedev.currencyconverter.ui.root.tabs.splash

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.saadahmedev.currencyconverter.R
import com.saadahmedev.currencyconverter.base.BaseFragment
import com.saadahmedev.currencyconverter.databinding.FragmentSplashBinding
import com.saadahmedev.currencyconverter.helper.observe
import com.saadahmedev.currencyconverter.ui.root.tabs.splash.viewmodel.SplashFragmentViewModel

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    override val title: String
        get() = ""
    override val isBackButtonVisible: Boolean
        get() = false

    private val splashFragmentViewModel by viewModels<SplashFragmentViewModel>()

    override fun onFragmentCreate(savedInstanceState: Bundle?) {
        splashFragmentViewModel.start()
        rootViewModel.isSplashActivity.set(true)
    }

    override fun observeData() {
        observe(splashFragmentViewModel.onSplashComplete) {
            navigate(R.id.action_splashFragment_to_homeFragment)
            rootViewModel.isSplashActivity.set(false)
        }
    }
}