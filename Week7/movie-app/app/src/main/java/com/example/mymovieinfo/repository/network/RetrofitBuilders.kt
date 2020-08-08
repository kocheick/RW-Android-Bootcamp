package com.example.mymovieinfo.repository.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "http://api.tvmaze.com"

fun buildClient(): OkHttpClient = OkHttpClient.Builder().build()

fun buildRetrofit(): Retrofit = Retrofit.Builder()
    .client(buildClient())
    .addConverterFactory(MoshiConverterFactory.create().asLenient())
    .baseUrl(BASE_URL)
    .build()

fun builApiService () :MovieApiService = buildRetrofit()
    .create(MovieApiService::class.java)
