package com.fgc.bitsobook.modules.contract

import androidx.lifecycle.MutableLiveData
import com.fgc.bitsobook.BitsoBookError
import com.fgc.bitsobook.api.models.result.ListPayLoad

interface BookContract {
    interface IModel {
        fun setUp()
        fun onGetBookSuccess(): MutableLiveData<List<ListPayLoad>>
        fun onGetBookServiceError(): MutableLiveData<BitsoBookError>
        fun onError(): MutableLiveData<String>
    }
}