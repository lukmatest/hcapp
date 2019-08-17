package com.lukma.hcapplication.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lukma.hcapplication.domain.content.Article
import com.lukma.hcapplication.domain.content.Product
import com.lukma.hcapplication.domain.content.usecase.GetHomeContentUseCase
import com.lukma.hcapplication.presentation.common.Resource
import com.lukma.hcapplication.shared.asResource

class MainViewModel(private val getHomeContentUseCase: GetHomeContentUseCase) : ViewModel() {
    private val contentMutable = MutableLiveData<Resource<Pair<List<Product>, List<Article>>>>()
    val content: LiveData<Resource<Pair<List<Product>, List<Article>>>> = contentMutable

    suspend fun getHomeContent() {
        contentMutable.postValue(Resource.Loading)
        getHomeContentUseCase.invoke(viewModelScope.coroutineContext)
            .asResource()
            .run(contentMutable::postValue)
    }
}
