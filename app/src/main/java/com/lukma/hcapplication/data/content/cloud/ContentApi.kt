package com.lukma.hcapplication.data.content.cloud

import retrofit2.http.GET

interface ContentApi {
    @GET("home")
    suspend fun getHomeContent(): HomeContentResponse
}
