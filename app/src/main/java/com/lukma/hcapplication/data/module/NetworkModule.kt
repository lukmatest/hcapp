package com.lukma.hcapplication.data.module

import com.lukma.hcapplication.BuildConfig
import com.lukma.hcapplication.data.content.cloud.ContentApi
import com.lukma.hcapplication.data.content.cloud.HomeContentResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

val networkModule = module {
    single {
        OkHttpClient.Builder().apply {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            }
            addInterceptor(loggingInterceptor)
        }.build()
    }

    single {
        val adapterFactory = PolymorphicJsonAdapterFactory
            .of(HomeContentResponse.Section::class.java, "section")
            .withSubtype(
                HomeContentResponse.Section.ProductSection::class.java,
                HomeContentResponse.SectionType.PRODUCTS.value
            )
            .withSubtype(
                HomeContentResponse.Section.ArticleSection::class.java,
                HomeContentResponse.SectionType.ARTICLES.value
            )

        Moshi.Builder().apply {
            add(adapterFactory)
            add(KotlinJsonAdapterFactory())
        }.build()
    }

    single {
        Retrofit.Builder().apply {
            addConverterFactory(MoshiConverterFactory.create(get<Moshi>()))
            client(get<OkHttpClient>())
            baseUrl(BuildConfig.BASE_API_URL)
        }.build()
    }

    single { get<Retrofit>().create<ContentApi>() }
}
