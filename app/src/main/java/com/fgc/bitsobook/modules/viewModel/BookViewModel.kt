package com.fgc.bitsobook.modules.viewModel

import androidx.lifecycle.MutableLiveData
import com.fgc.bitsobook.BitsoBookError
import com.fgc.bitsobook.api.Retrofit
import com.fgc.bitsobook.api.models.result.AvailableBooksResult
import com.fgc.bitsobook.api.models.result.ListPayLoad
import com.fgc.bitsobook.base.BaseViewModel
import com.fgc.bitsobook.modules.contract.BookContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BookViewModel @Inject constructor() : BaseViewModel(), BookContract.IModel,
    Callback<AvailableBooksResult> {

    private val bookListMLD: MutableLiveData<List<ListPayLoad>> = MutableLiveData()
    private val bookServiceErrorMLD: MutableLiveData<BitsoBookError> = MutableLiveData()
    private val errorMLD: MutableLiveData<String> = MutableLiveData()

    override fun setUp() {
        val api = Retrofit.provideApiService()
        val call = api.getAvailableBooks()
        call.enqueue(this)
    }

    override fun onGetBookSuccess(): MutableLiveData<List<ListPayLoad>> = bookListMLD
    override fun onGetBookServiceError(): MutableLiveData<BitsoBookError> = bookServiceErrorMLD
    override fun onError(): MutableLiveData<String> = errorMLD
    override fun onFailure(call: Call<AvailableBooksResult>, t: Throwable) {
        t.printStackTrace()
        bookServiceErrorMLD.value = BitsoBookError(t)
    }

    override fun onResponse(call: Call<AvailableBooksResult>, response: Response<AvailableBooksResult>) {
        when {
            response.body() == null -> {
                errorMLD.value = "Error, intente de nuevo"
            }
            response.body()!!.success!! -> {
                bookListMLD.value = response.body()!!.payload
            }
            else -> {
                errorMLD.value = "response.body()!!.success!!"
            }
        }
    }

}