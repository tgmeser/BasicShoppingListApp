package com.babyapps.shoppinglist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.babyapps.shoppinglist.R
import com.babyapps.shoppinglist.data.entity.ShoppingItem
import com.babyapps.shoppinglist.ui.viewmodel.ShoppingViewModel
import kotlinx.android.synthetic.main.item_shopping.view.*

class ShoppingAdapter(
    var shoppingItemList: List<ShoppingItem>,
    private var viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder>() {

    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_shopping, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentItem = shoppingItemList[position]
        holder.itemView.tvName.text = currentItem.name
        holder.itemView.tvAmount.text = "${currentItem.amount}"

        holder.itemView.ivPlus.setOnClickListener {
            currentItem.amount++
            viewModel.upsert(currentItem)
        }
        holder.itemView.ivMinus.setOnClickListener {
            if (currentItem.amount > 1) {
                currentItem.amount--
                viewModel.upsert(currentItem)
            }
        }
        holder.itemView.ivDelete.setOnClickListener {
            viewModel.delete(currentItem)
        }

    }

    override fun getItemCount(): Int = shoppingItemList.size
}