package com.lukma.hcapplication.content

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.lukma.hcapplication.data.common.MissingPropertyException
import com.lukma.hcapplication.domain.content.Article
import com.lukma.hcapplication.domain.content.ContentRepository
import com.lukma.hcapplication.domain.content.HomeContent
import com.lukma.hcapplication.domain.content.Product
import com.lukma.hcapplication.domain.content.usecase.GetHomeContentUseCase
import com.lukma.hcapplication.presentation.common.Resource
import com.lukma.hcapplication.presentation.main.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ContentViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Mock
    lateinit var mockRepository: ContentRepository

    @Mock
    lateinit var observer: Observer<Resource<HomeContent>>

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.IO)
        viewModel = MainViewModel(GetHomeContentUseCase(mockRepository))
        viewModel.content.observeForever(observer)
    }

    @Test
    fun testGetHomeContentSuccess() = runBlocking {
        val data = HomeContent(
            HomeContent.ProductSection(
                listOf(Product("name", "image", "link"))
            ),
            HomeContent.ArticleSection(
                "sectionTitle",
                listOf(Article("title", "image", "link"))
            )
        )

        `when`(mockRepository.getHomeContent()).thenReturn(data)
        viewModel.getHomeContent()

        verify(observer).onChanged(Resource.Success(data))
    }

    @Test
    fun testGetHomeContentFailure() = runBlocking {
        val error = MissingPropertyException("data")
        given(mockRepository.getHomeContent()).willAnswer { throw error }
        viewModel.getHomeContent()

        verify(observer).onChanged(Resource.Failure(error))
    }
}