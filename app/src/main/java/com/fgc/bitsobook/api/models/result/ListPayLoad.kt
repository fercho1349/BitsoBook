package com.fgc.bitsobook.api.models.result

data class ListPayLoad (
    val book: String? = null,
    val minimum_amount: String? = null,
    val maximum_amount: String? = null,
    val minimum_price: String? = null,
    val maximum_price: String? = null,
    val minimum_value: String? = null,
    val maximum_value: String? = null
)