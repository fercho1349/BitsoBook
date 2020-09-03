package com.fgc.bitsobook

import retrofit2.HttpException

class BitsoBookError {
    var errorType: Int

    constructor(error: Throwable) {
        errorType = when {
            (error is HttpException) -> getError(error)
            else -> R.string.fallback_error
        }
    }

    private fun getError(error: HttpException): Int =
        when (error.code()) {
            400 -> R.string.error_400
            else -> R.string.fallback_error
        }
}