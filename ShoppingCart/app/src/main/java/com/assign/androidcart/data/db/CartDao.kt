package com.assign.androidcart.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_items")
    fun getAll(): Flow<List<CartItemEntity>>

    @Upsert
    suspend fun upsert(item: CartItemEntity)

    @Delete
    suspend fun delete(item: CartItemEntity)

    @Query("DELETE FROM cart_items WHERE id = :id")
    suspend fun deleteById(id: Int)
}