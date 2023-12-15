package com.saadahmedev.currencyconverter.ui.root

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saadahmedev.currencyconverter.R
import com.saadahmedev.currencyconverter.base.BaseActivity
import com.saadahmedev.currencyconverter.databinding.ActivityRootBinding
import com.saadahmedev.currencyconverter.databinding.AppToolbarBinding

class RootActivity : BaseActivity<ActivityRootBinding>(ActivityRootBinding::inflate) {

    override val toolbarBinding: AppToolbarBinding
        get() = binding.appToolbar

    override fun onActivityCreate(savedInstanceState: Bundle?) {
        binding.viewmodel = rootViewModel
    }

    override fun observeData() {}
}