package com.babyapps.shoppinglist.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.babyapps.shoppinglist.data.entity.ShoppingItem
import com.babyapps.shoppinglist.data.repository.ShoppingRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository: ShoppingRepository
): ViewModel() {

    fun upsert(item: ShoppingItem) = GlobalScope.launch {
        repository.upsert(item)
    }
    fun delete(item: ShoppingItem) = GlobalScope.launch {
        repository.delete(item)
    }
    fun getAllRecords() = repository.getAllRecords()
}