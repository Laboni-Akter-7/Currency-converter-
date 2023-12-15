package com.saadahmedev.currencyconverter.ui.root.tabs.splash.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashFragmentViewModel : ViewModel() {

    private val _onSplashComplete = MutableLiveData<Boolean>()
    val onSplashComplete: LiveData<Boolean>
        get() = _onSplashComplete

    fun start() {
        startSplash()
    }

    private fun startSplash() {
        Handler(Looper.getMainLooper()).postDelayed({
            _onSplashComplete.postValue(true)
        }, 2000)
    }
}