package com.saadahmedev.currencyconverter.di

import com.saadahmedev.currencyconverter.data.repository.CurrencyConverterRepositoryImpl
import com.saadahmedev.currencyconverter.data.source.CurrencyApi
import com.saadahmedev.currencyconverter.domain.repository.CurrencyConverterRepository
import com.saadahmedev.currencyconverter.util.AppConstants.Api.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CurrencyConverterModule {

    @Provides
    @Singleton
    fun provideCurrencyApi(): CurrencyApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyApi::class.java)

    @Provides
    @Singleton
    fun provideCurrencyConverterRepository(currencyApi: CurrencyApi): CurrencyConverterRepository =
        CurrencyConverterRepositoryImpl(currencyApi)
}