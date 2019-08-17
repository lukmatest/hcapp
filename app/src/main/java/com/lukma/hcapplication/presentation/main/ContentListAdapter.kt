package com.lukma.hcapplication.presentation.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lukma.hcapplication.R
import com.lukma.hcapplication.domain.content.Article
import com.lukma.hcapplication.domain.content.HomeContent
import com.lukma.hcapplication.domain.content.Product

class ContentListAdapter(
    private val onProductItemClicked: (Product) -> Unit,
    private val onArticleItemClicked: (Article) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val products = mutableListOf<Product>()
    private val articles = mutableListOf<Article>()

    fun submit(data: HomeContent) {
        products.clear()
        articles.clear()

        products.addAll(data.productSection.products)
        articles.addAll(data.articleSection.articles)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        R.layout.main_header_item -> HeaderHolder.newInstance(parent, onProductItemClicked)
        R.layout.main_article_item -> ArticleHolder.newInstance(parent, onArticleItemClicked)
        else -> throw IllegalStateException("Unsupported view type")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderHolder -> holder.onBind(products)
            is ArticleHolder -> articles.getOrNull(position - 1)?.run(holder::onBind)
        }
    }

    override fun getItemCount() = articles.size + 1

    override fun getItemViewType(position: Int) = if (position == 0) {
        R.layout.main_header_item
    } else {
        R.layout.main_article_item
    }
}
