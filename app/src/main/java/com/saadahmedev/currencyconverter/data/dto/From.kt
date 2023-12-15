package com.saadahmedev.currencyconverter.data.dto

import com.google.gson.annotations.SerializedName


data class From (

  @SerializedName("rate"     ) var rate     : Double?    = null,
  @SerializedName("currency" ) var currency : String? = null

)