package com.babyapps.shoppinglist.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.babyapps.shoppinglist.data.entity.ShoppingItem

@Dao
interface ShoppingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("SELECT * FROM shopping_table")
    fun getAllRecords(): LiveData<List<ShoppingItem>>
}