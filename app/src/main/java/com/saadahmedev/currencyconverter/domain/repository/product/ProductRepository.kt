package com.saadahmedev.currencyconverter.domain.repository.product

import com.saadahmedev.currencyconverter.data.dto.product.ProductResponse
import com.saadahmedev.currencyconverter.data.dto.product.Products

interface ProductRepository {

    suspend fun getProducts(limit: Int): ProductResponse

    suspend fun getProduct(id: Int): Products
}