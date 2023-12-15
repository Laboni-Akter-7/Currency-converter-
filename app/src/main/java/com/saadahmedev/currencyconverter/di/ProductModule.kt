package com.saadahmedev.currencyconverter.di

import com.saadahmedev.currencyconverter.data.repository.product.ProductRepositoryImpl
import com.saadahmedev.currencyconverter.data.source.ProductApi
import com.saadahmedev.currencyconverter.domain.repository.product.ProductRepository
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
object ProductModule {

    @Provides
    @Singleton
    fun provideProductApi(): ProductApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(productApi: ProductApi): ProductRepository {
        return ProductRepositoryImpl(productApi)
    }
}