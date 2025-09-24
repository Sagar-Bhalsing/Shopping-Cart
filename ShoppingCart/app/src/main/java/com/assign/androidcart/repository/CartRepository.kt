package com.assign.androidcart.repository

import com.assign.androidcart.data.db.CartDao
import com.assign.androidcart.data.db.CartItemEntity
import com.assign.androidcart.data.model.ProductDto
import com.assign.androidcart.data.network.FakeStoreApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor(
    private val api: FakeStoreApi,
    private val dao: CartDao
) {
    suspend fun fetchProducts(): List<ProductDto> = api.getProducts()

    fun getCartItems(): Flow<List<CartItemEntity>> = dao.getAll()

    suspend fun addToCart(product: ProductDto) {
        val entity = CartItemEntity(
            id = product.id,
            title = product.title,
            price = product.price,
            image = product.image,
            quantity = 1
        )
        dao.upsert(entity)
    }


    suspend fun removeFromCart(item: CartItemEntity) {
        dao.delete(item)
    }

    suspend fun removeById(id: Int) {
        dao.deleteById(id)
    }

    suspend fun updateQuantity(item: CartItemEntity) {
        dao.upsert(item)
    }
}
