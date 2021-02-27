package com.babyapps.shoppinglist.ui.dialog

import com.babyapps.shoppinglist.data.entity.ShoppingItem

interface ItemDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)

}