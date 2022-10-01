package com.chrissloan.paw_some.data.api

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiFactory {

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }
    private val moshi: Moshi by lazy {
        Moshi.Builder().build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    fun create(): CatApi = retrofit.create(CatApi::class.java)
}
