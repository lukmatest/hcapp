package com.lukma.hcapplication.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lukma.hcapplication.domain.content.HomeContent
import com.lukma.hcapplication.domain.content.usecase.GetHomeContentUseCase
import com.lukma.hcapplication.presentation.common.Resource
import com.lukma.hcapplication.shared.asResource

class MainViewModel(private val getHomeContentUseCase: GetHomeContentUseCase) : ViewModel() {
    private val contentMutable = MutableLiveData<Resource<HomeContent>>()
    val content: LiveData<Resource<HomeContent>> = contentMutable

    suspend fun getHomeContent() {
        contentMutable.postValue(Resource.Loading)
        getHomeContentUseCase.invoke(viewModelScope.coroutineContext)
            .asResource()
            .run(contentMutable::postValue)
    }
}
