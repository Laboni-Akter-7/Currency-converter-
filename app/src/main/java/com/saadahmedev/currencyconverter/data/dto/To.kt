package com.saadahmedev.currencyconverter.data.dto

import com.google.gson.annotations.SerializedName


data class To (

  @SerializedName("rate"     ) var rate     : Double? = null,
  @SerializedName("currency" ) var currency : String? = null

)