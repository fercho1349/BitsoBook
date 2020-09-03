package com.fgc.bitsobook.api.models.result

data class AvailableBooksResult (
    val success: Boolean? = null,
    val payload: ArrayList<ListPayLoad>? = null
)