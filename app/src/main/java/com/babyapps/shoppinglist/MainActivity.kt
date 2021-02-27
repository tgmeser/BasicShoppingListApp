package com.babyapps.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.babyapps.shoppinglist.adapter.ShoppingAdapter
import com.babyapps.shoppinglist.data.entity.ShoppingItem
import com.babyapps.shoppinglist.ui.dialog.ItemDialog
import com.babyapps.shoppinglist.ui.dialog.ItemDialogListener
import com.babyapps.shoppinglist.ui.viewmodel.ShoppingViewModel
import com.babyapps.shoppinglist.ui.viewmodel.ShoppingViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()
    lateinit var viewModel: ShoppingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this,factory).get(ShoppingViewModel::class.java)
        val shoppingAdapter = ShoppingAdapter(listOf(),viewModel)

        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = shoppingAdapter

        viewModel.getAllRecords().observe(this, Observer {
            shoppingAdapter.shoppingItemList = it
            shoppingAdapter.notifyDataSetChanged()
        })
        fab.setOnClickListener {
            ItemDialog(this,object: ItemDialogListener{
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }
}