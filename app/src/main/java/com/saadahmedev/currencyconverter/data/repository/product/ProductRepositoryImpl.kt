package com.saadahmedev.currencyconverter.data.repository.product

import com.saadahmedev.currencyconverter.data.dto.product.ProductResponse
import com.saadahmedev.currencyconverter.data.dto.product.Products
import com.saadahmedev.currencyconverter.data.source.ProductApi
import com.saadahmedev.currencyconverter.domain.repository.product.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productApi: ProductApi) : ProductRepository {

    override suspend fun getProducts(limit: Int): ProductResponse {
        return productApi.getProducts(limit)
    }

    override suspend fun getProduct(id: Int): Products {
        return productApi.getProduct(id)
    }
}