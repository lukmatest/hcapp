package com.lukma.hcapplication.presentation.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lukma.hcapplication.domain.content.Product

class ProductListAdapter(
    private val items: List<Product>,
    private val onItemClicked: (Product) -> Unit
) : RecyclerView.Adapter<ProductHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProductHolder.newInstance(parent, onItemClicked)

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount() = items.size
}
