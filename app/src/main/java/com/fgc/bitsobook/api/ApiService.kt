package com.fgc.bitsobook.api

import com.fgc.bitsobook.api.models.result.AvailableBooksResult
import com.fgc.bitsobook.api.models.result.InfoTickerResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v3/available_books/")
    fun getAvailableBooks(): retrofit2.Call<AvailableBooksResult>

    @GET("v3/ticker/")
    fun getInfoTicker(@Query("book") book : String): retrofit2.Call<InfoTickerResult>

}