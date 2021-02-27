package com.babyapps.shoppinglist.ui.dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.babyapps.shoppinglist.R
import com.babyapps.shoppinglist.data.entity.ShoppingItem
import kotlinx.android.synthetic.main.item_dialog.*



class ItemDialog(context: Context, var dialogListener: ItemDialogListener) :
    AppCompatDialog(context)  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.item_dialog)

        tvAdd.setOnClickListener {
            val name = etName.text.toString()
            val amount = etAmount.text.toString().toInt()
            if(name.isEmpty()) {
                Toast.makeText(context, "Please enter a name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name, amount)
            dialogListener.onAddButtonClicked(item)
            dismiss()
        }

        tvCancel.setOnClickListener {
            cancel()
        }
    }
}