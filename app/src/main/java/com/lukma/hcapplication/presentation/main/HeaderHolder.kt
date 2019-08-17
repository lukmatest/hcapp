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
    private lateinit var onItemClicked: (Product) -> Unit

    private val recyclerView = itemView.recyclerView
    private val sectionTextView = itemView.sectionTextView

    private fun onCreate(onItemClicked: (Product) -> Unit) {
        this.onItemClicked = onItemClicked
    }

    fun onBind(products: List<Product>, articleSectionTitle: String) {
        with(recyclerView) {
            layoutManager = GridLayoutManager(itemView.context, 3)
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimension(R.dimen.default_component_space_medium).toInt(),
                    MarginItemDecoration.Mode.Grid(3)
                )
            )
            adapter = ProductListAdapter(products, onItemClicked)
        }
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
