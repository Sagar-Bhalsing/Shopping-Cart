package com.assign.androidcart.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [CartItemEntity::class], version = 2)
abstract class CartDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
            }
        }
    }
}