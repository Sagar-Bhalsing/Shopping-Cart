package com.assign.androidcart.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Double,
    val image: String,
    @ColumnInfo(defaultValue = "1") val quantity: Int = 1
)