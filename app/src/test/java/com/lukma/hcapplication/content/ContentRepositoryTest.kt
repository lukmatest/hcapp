package com.lukma.hcapplication.content

import com.lukma.hcapplication.data.content.ContentDataRepository
import com.lukma.hcapplication.data.content.cloud.ContentApi
import com.lukma.hcapplication.data.content.cloud.HomeContentResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ContentRepositoryTest {
    private lateinit var repository: ContentDataRepository

    @Mock
    lateinit var mockApi: ContentApi

    @Before
    fun setup() {
        repository = ContentDataRepository(mockApi)
    }

    @Test
    fun testRepositoryNotNull() = runBlocking {
        val response = HomeContentResponse(
            listOf(
                HomeContentResponse.Section.ProductSection(listOf()),
                HomeContentResponse.Section.ArticleSection("sectionTitle", listOf())
            )
        )
        `when`(mockApi.getHomeContent()).thenReturn(response)

        val result = repository.getHomeContent()
        assertNotNull(result)
    }
}