package com.lukma.hcapplication.content

import com.lukma.hcapplication.data.common.MissingPropertyException
import com.lukma.hcapplication.domain.common.Either
import com.lukma.hcapplication.domain.content.ContentRepository
import com.lukma.hcapplication.domain.content.HomeContent
import com.lukma.hcapplication.domain.content.usecase.GetHomeContentUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ContentUseCaseTest {
    private lateinit var useCase: GetHomeContentUseCase

    @Mock
    lateinit var mockRepository: ContentRepository

    @Before
    fun setup() {
        useCase = GetHomeContentUseCase(mockRepository)
    }

    @Test
    fun testUseCaseNotNull() = runBlocking {
        val result = useCase.invoke(Dispatchers.IO)
        assertNotNull(result)
    }

    @Test
    fun testUseCaseSuccess() = runBlocking {
        val data = HomeContent(
            HomeContent.ProductSection(listOf()),
            HomeContent.ArticleSection("sectionTitle", listOf())
        )
        `when`(mockRepository.getHomeContent()).thenReturn(data)

        val result = useCase.invoke(Dispatchers.IO)
        val expected = Either.Value(data)
        assertEquals(result, expected)
    }

    @Test
    fun testUseCaseFailure() = runBlocking {
        val error = MissingPropertyException("data")
        given(mockRepository.getHomeContent()).willAnswer { throw error }

        val result = useCase.invoke(Dispatchers.IO)
        val expected = Either.Error(error)
        assertEquals(result, expected)
    }
}