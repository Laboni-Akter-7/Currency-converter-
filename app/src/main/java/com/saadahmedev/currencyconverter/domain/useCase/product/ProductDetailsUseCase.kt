package com.saadahmedev.currencyconverter.domain.useCase.product

import com.saadahmedev.currencyconverter.domain.model.product.ProductDetail
import com.saadahmedev.currencyconverter.domain.repository.product.ProductRepository
import com.saadahmedev.currencyconverter.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductDetailsUseCase @Inject constructor(private val productRepository: ProductRepository) {

    operator fun invoke(id: Int): Flow<ResponseState<ProductDetail>> = flow {
        try {
            emit(ResponseState.Loading())

            val product = productRepository.getProduct(id).toProductDetail()
            emit(ResponseState.Success(product))

        }
        catch (e: Exception) {
            emit(ResponseState.Error(e.localizedMessage?: "Unexpected error occurred"))
        }
    }
}