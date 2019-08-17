package com.lukma.hcapplication.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukma.hcapplication.domain.content.Article
import com.lukma.hcapplication.domain.content.Product
import com.lukma.hcapplication.presentation.common.Resource

class MainViewModel : ViewModel() {
    private val productsMutable = MutableLiveData<Resource<List<Product>>>()
    val products: LiveData<Resource<List<Product>>> = productsMutable

    private val articlesMutable = MutableLiveData<Resource<List<Article>>>()
    val articles: LiveData<Resource<List<Article>>> = articlesMutable
}
