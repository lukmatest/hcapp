package com.lukma.hcapplication.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lukma.hcapplication.R
import com.lukma.hcapplication.domain.content.Product
import kotlinx.android.synthetic.main.main_header_item.view.*

class HeaderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var listAdapter: ProductListAdapter
    private val recyclerView = itemView.recyclerView

    private fun onCreate(onItemClicked: (Product) -> Unit) {
        listAdapter = ProductListAdapter(onItemClicked)
    }

    fun onBind(items: List<Product>) {
        listAdapter.addItems(items)
        with(recyclerView) {
            layoutManager = GridLayoutManager(itemView.context, 3)
            adapter = listAdapter
        }
    }

    companion object {
        fun newInstance(parent: ViewGroup, onItemClicked: (Product) -> Unit) =
            HeaderHolder(
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.main_header_item, parent, false)
            ).apply {
                onCreate(onItemClicked)
            }
    }
}
