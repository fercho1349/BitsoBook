package com.fgc.bitsobook.api.models.result

data class ListPayLoad (
    val book: String? = null,
    val minimum_amount: Float? = null,
    val maximum_amount: String? = null,
    val minimum_price: Float? = null,
    val maximum_price: String? = null,
    val minimum_value: Float? = null,
    val maximum_value: String? = null
)