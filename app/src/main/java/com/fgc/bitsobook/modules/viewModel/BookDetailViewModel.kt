package com.fgc.bitsobook.modules.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fgc.bitsobook.BitsoBookError
import com.fgc.bitsobook.api.Retrofit
import com.fgc.bitsobook.api.models.result.InfoTickerResult
import com.fgc.bitsobook.api.models.result.PayLoad
import com.fgc.bitsobook.modules.contract.BookDetailContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookDetailViewModel : ViewModel(), BookDetailContract.IModel, Callback<InfoTickerResult> {

    private val bookDetailMLD: MutableLiveData<PayLoad> = MutableLiveData()
    private val bookDetailServiceErrorMLD: MutableLiveData<BitsoBookError> = MutableLiveData()
    private val errorMLD: MutableLiveData<String> = MutableLiveData()

    override fun setUp(book: String) {
        val api = Retrofit.provideApiService()
        val call = api.getInfoTicker(book)
        call.enqueue(this)
    }

    override fun onGetBookDetailSucces(): MutableLiveData<PayLoad> = bookDetailMLD
    override fun onGetBookDetailServiceError(): MutableLiveData<BitsoBookError> = bookDetailServiceErrorMLD
    override fun onError(): MutableLiveData<String> = errorMLD
    override fun onFailure(call: Call<InfoTickerResult>, t: Throwable) {
        t.printStackTrace()
        bookDetailServiceErrorMLD.value = BitsoBookError(t)
    }

    override fun onResponse(call: Call<InfoTickerResult>, response: Response<InfoTickerResult>) {
        when {
            response.body() == null -> {
                errorMLD.value = "Error, intente de nuevo"
            }
            response.body()!!.success!! -> {
                bookDetailMLD.value = response.body()!!.payload
            }
            else -> {
                errorMLD.value = "response.body()!!.success!!"
            }
        }
    }

}