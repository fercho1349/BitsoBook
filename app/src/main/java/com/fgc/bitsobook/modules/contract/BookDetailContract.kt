package com.fgc.bitsobook.modules.contract

import androidx.lifecycle.MutableLiveData
import com.fgc.bitsobook.BitsoBookError
import com.fgc.bitsobook.api.models.result.PayLoad

interface BookDetailContract {
    interface IModel {
        fun setUp(book: String)
        fun onGetBookDetailSucces(): MutableLiveData<PayLoad>
        fun onGetBookDetailServiceError(): MutableLiveData<BitsoBookError>
        fun onError(): MutableLiveData<String>
    }
}