package com.saadahmedev.currencyconverter.ui.root.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

open class RootViewModel : ViewModel() {

    open var isSplashActivity = ObservableField(false)
}