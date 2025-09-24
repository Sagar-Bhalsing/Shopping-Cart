package com.assign.androidcart.data.network

import com.assign.androidcart.data.model.ProductDto
import retrofit2.http.GET

interface FakeStoreApi {
    @GET("products")
    suspend fun getProducts(): List<ProductDto>
}
