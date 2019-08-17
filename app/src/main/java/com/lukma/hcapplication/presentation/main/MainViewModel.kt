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
    private val homeContentMutable = MutableLiveData<Resource<Pair<List<Product>, List<Article>>>>()
    val homeContent: LiveData<Resource<Pair<List<Product>, List<Article>>>> = homeContentMutable

    suspend fun getHomeContent() {
        getHomeContentUseCase.invoke(viewModelScope.coroutineContext)
            .asResource()
            .run(homeContentMutable::postValue)
    }
}
