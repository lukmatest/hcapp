package com.lukma.hcapplication.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lukma.hcapplication.R
import com.lukma.hcapplication.domain.content.Product
import com.lukma.hcapplication.presentation.common.ImageRequestListener
import com.lukma.hcapplication.presentation.module.GlideApp
import kotlinx.android.synthetic.main.main_product_item.view.*

class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var item: Product? = null

    private val thumbnailImageView = itemView.thumbnailImageView
    private val progressBar = itemView.progressBar
    private val titleTextView = itemView.titleTextView

    private fun onCreate(onItemClicked: (Product) -> Unit) {
        itemView.setOnClickListener { item?.run(onItemClicked) }
    }

    fun onBind(item: Product) {
        this.item = item
        GlideApp.with(itemView)
            .load(item.image)
            .listener(ImageRequestListener(progressBar))
            .into(thumbnailImageView)
        titleTextView.text = item.name
    }

    companion object {
        fun newInstance(parent: ViewGroup, onItemClicked: (Product) -> Unit) =
            ProductHolder(
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.main_product_item, parent, false)
            ).apply {
                onCreate(onItemClicked)
            }
    }
}
