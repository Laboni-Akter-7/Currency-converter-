package com.saadahmedev.currencyconverter.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.saadahmedev.currencyconverter.databinding.AppToolbarBinding
import com.saadahmedev.currencyconverter.helper.SnackbarActionListener
import com.saadahmedev.currencyconverter.helper.observe
import com.saadahmedev.currencyconverter.helper.onClicked
import com.saadahmedev.currencyconverter.helper.snackBar
import com.saadahmedev.currencyconverter.helper.toast
import com.saadahmedev.currencyconverter.ui.root.viewmodel.RootViewModel
import retrofit2.Call

abstract class BaseActivity<BINDING: ViewBinding>(private val bindingInflater: (inflater: LayoutInflater) -> BINDING) : AppCompatActivity() {

    private lateinit var _binding: BINDING
    private val toolbarViewModel by viewModels<ToolbarViewModel>()
    val rootViewModel by viewModels<RootViewModel>()

    val binding: BINDING
        get() = _binding

    abstract val toolbarBinding: AppToolbarBinding?

    abstract fun onActivityCreate(savedInstanceState: Bundle?)

    abstract fun observeData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(binding.root)
        initToolbar()
        onActivityCreate(savedInstanceState)
        observeData()
    }

    private fun initToolbar() {
        if (toolbarBinding != null) {
            observe(toolbarViewModel.title) {
                toolbarBinding?.toolbarTitle?.text = it
            }

            observe(toolbarViewModel.isBackButtonVisible) {
                toolbarBinding?.toolbarBtn?.visibility = if (it) View.VISIBLE else View.GONE
            }
        }

        toolbarBinding?.toolbarBtn?.onClicked {
            super.onBackPressedDispatcher.onBackPressed()
        }
    }

    fun String?.shortSnackBar(action: String, listener: SnackbarActionListener) {
        this?.let { showSnackBar(it, action, Snackbar.LENGTH_SHORT, listener) }
    }

    fun String?.longSnackBar(action: String, listener: SnackbarActionListener) {
        this?.let { showSnackBar(it, action, Snackbar.LENGTH_LONG, listener) }
    }

    fun String?.shortSnackBar() {
        this?.let {
            showSnackBar(it, "Close", Snackbar.LENGTH_SHORT, object : SnackbarActionListener {
                override fun onActionClicked(snackbar: Snackbar) {
                    snackbar.dismiss()
                }
            })
        }
    }

    fun String?.longSnackBar() {
        this?.let {
            showSnackBar(it, "Close", Snackbar.LENGTH_LONG, object : SnackbarActionListener {
                override fun onActionClicked(snackbar: Snackbar) {
                    snackbar.dismiss()
                }
            })
        }
    }

    fun String?.shortToast() {
        this?.let { showToast(it, Toast.LENGTH_SHORT) }
    }

    fun String?.longToast() {
        this?.let { showToast(it, Toast.LENGTH_LONG) }
    }

    private fun showSnackBar(message: String, action: String, duration: Int, listener: SnackbarActionListener) {
        snackBar(_binding.root, message, action, duration, listener)
    }

    private fun showToast(message: String, duration: Int) {
        toast(this, message, duration)
    }
}