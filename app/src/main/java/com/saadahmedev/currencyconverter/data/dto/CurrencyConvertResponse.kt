package com.saadahmedev.currencyconverter.data.dto

import com.google.gson.annotations.SerializedName
import com.saadahmedev.currencyconverter.domain.model.CurrencyResponse
import java.text.DecimalFormat


data class CurrencyConvertResponse (

  @SerializedName("total"     ) var total     : Double? = null,
  @SerializedName("rate"      ) var rate      : Double? = null,
  @SerializedName("from"      ) var from      : From?   = From(),
  @SerializedName("to"        ) var to        : To?     = To(),
  @SerializedName("timestamp" ) var timestamp : Int?    = null

) {
  fun toCurrencyResponse() =
    CurrencyResponse(
      from = from?.currency ?: "",
      to = to?.currency ?: "",
      result = DecimalFormat("#.##").format(total),
      rate = DecimalFormat("#.##").format(rate)
    )
}