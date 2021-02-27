package com.babyapps.shoppinglist.data.repository

import com.babyapps.shoppinglist.data.db.ShoppingDatabase
import com.babyapps.shoppinglist.data.entity.ShoppingItem

class ShoppingRepository(private val database: ShoppingDatabase) {

    suspend fun upsert(item: ShoppingItem) = database.getShoppingDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = database.getShoppingDao().delete(item)

    fun getAllRecords() = database.getShoppingDao().getAllRecords()
}