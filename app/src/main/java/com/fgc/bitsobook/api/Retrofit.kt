package com.fgc.bitsobook.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {

    companion object {
        fun provideApiService(): ApiService {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://api.bitso.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }

}