package com.lukma.hcapplication.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.lukma.hcapplication.R
import com.lukma.hcapplication.domain.content.Article
import com.lukma.hcapplication.presentation.common.ImageRequestListener
import com.lukma.hcapplication.presentation.module.GlideApp
import kotlinx.android.synthetic.main.main_article_item.view.*

class ArticleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var item: Article? = null

    private val thumbnailImageView = itemView.thumbnailImageView
    private val progressBar = itemView.progressBar
    private val titleTextView = itemView.titleTextView

    private val options = RequestOptions()
        .placeholder(R.color.colorAccent)
        .transform(CenterCrop(), RoundedCorners(16))

    private fun onCreate(onItemClicked: (Article) -> Unit) {
        itemView.setOnClickListener { item?.run(onItemClicked) }
    }

    fun onBind(item: Article) {
        this.item = item
        GlideApp.with(itemView)
            .load(item.image)
            .apply(options)
            .listener(ImageRequestListener(progressBar))
            .into(thumbnailImageView)
        titleTextView.text = item.title
    }

    companion object {
        fun newInstance(parent: ViewGroup, onItemClicked: (Article) -> Unit) =
            ArticleHolder(
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.main_article_item, parent, false)
            ).apply {
                onCreate(onItemClicked)
            }
    }
}
