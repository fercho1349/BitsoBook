package com.fgc.bitsobook.api.models.result

data class PayLoad (
    val book: String? = null,
    val volume: Float? = null,
    val high: String? = null,
    val last: Float? = null,
    val low: String? = null,
    val vwap: Float? = null,
    val ask: String? = null,
    val bid: Float? = null,
    val created_at: String? = null
)