package com.babyapps.shoppinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.babyapps.shoppinglist.data.entity.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase : RoomDatabase() {

    abstract fun getShoppingDao(): ShoppingDao

    //Creating DB construction by Singleton Pattern
    companion object {

        @Volatile
        private var INSTANCE: ShoppingDatabase? = null
        private val LOCK: Any = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: createDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ShoppingDatabase::class.java,"ShoppingDatabase.db"
        ).build()
    }
}