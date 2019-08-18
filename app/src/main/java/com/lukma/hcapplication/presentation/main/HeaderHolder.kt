package com.lukma.hcapplication.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lukma.hcapplication.R
import com.lukma.hcapplication.domain.content.Product
import com.lukma.hcapplication.presentation.common.MarginItemDecoration
import kotlinx.android.synthetic.main.main_header_item.view.*

class HeaderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val productRecyclerView = itemView.productRecyclerView
    private val sectionTextView = itemView.sectionTextView

    private lateinit var listAdapter: ProductListAdapter

    private fun onCreate(onItemClicked: (Product) -> Unit) {
        listAdapter = ProductListAdapter(onItemClicked)
        with(productRecyclerView) {
            layoutManager = GridLayoutManager(itemView.context, 3)
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimension(R.dimen.default_component_space_medium).toInt(),
                    MarginItemDecoration.Mode.Grid(3)
                )
            )
            adapter = listAdapter
        }
    }

    fun onBind(products: List<Product>, articleSectionTitle: String) {
        listAdapter.submit(products)
        sectionTextView.text = articleSectionTitle
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
