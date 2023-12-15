package com.saadahmedev.currencyconverter.helper

import android.content.Context
import com.saadahmedev.currencyconverter.util.SessionManager

fun Context.getToken(): String? = SessionManager.getInstance(this).token
fun Context.getBearerToken(): String? = SessionManager.getInstance(this).bearerToken
fun Context.getPhone(): String? = SessionManager.getInstance(this).number