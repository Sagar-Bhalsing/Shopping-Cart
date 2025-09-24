package com.assign.androidcart.di

import android.content.Context
import androidx.room.Room
import com.assign.androidcart.data.db.CartDao
import com.assign.androidcart.data.db.CartDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CartDatabase {
        return Room.databaseBuilder(context, CartDatabase::class.java, "cart_db")
            .addMigrations(CartDatabase.MIGRATION_1_2)
            .build()
    }

    @Provides
    fun provideCartDao(db: CartDatabase): CartDao = db.cartDao()
}