package com.fgc.bitsobook.modules.view

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fgc.bitsobook.BitsoBookError
import com.fgc.bitsobook.R
import com.fgc.bitsobook.api.models.result.PayLoad
import com.fgc.bitsobook.base.BaseActivity
import com.fgc.bitsobook.modules.viewModel.BookDetailViewModel
import kotlinx.android.synthetic.main.book_detail.*
import kotlinx.android.synthetic.main.shimmer_book_detail.*

class BookDetailActivity: BaseActivity() {

    private lateinit var bookDetailViewModel: BookDetailViewModel
    private var book: String? = null

    @LayoutRes
    override fun getLayoutResource(): Int = R.layout.book_detail

    override fun setUp() {
        getRequest()
    }

    override fun initVMObservers() {
        super.initVMObservers()
        bookDetailViewModel = ViewModelProvider(this).get(BookDetailViewModel::class.java)
        bookDetailViewModel.onGetBookDetailSucces().observe(this, Observer { onBookDetailRetrieved(it) })
        bookDetailViewModel.onGetBookDetailServiceError().observe(this, Observer { onBookDetailRetrieveError(it) })
        bookDetailViewModel.onError().observe(this, Observer { onError(it) })
    }

    override fun initView() {
        super.initView()
        book = intent.getStringExtra("book")
    }

    private fun onBookDetailRetrieved(it: PayLoad) {
        shimmerView_bookDetail.stopShimmer()
        txt_bidprice.text = it.bid
        txt_askprice.text = it.ask
        txt_daylow.text = it.low
        txt_dayhigh.text = it.high
        txt_volume.text = it.volume
        txt_spread.text = it.last
    }

    private fun onBookDetailRetrieveError(it: BitsoBookError) {
        Log.e(javaClass.simpleName, it.toString())
        shimmerView_bookDetail.stopShimmer()
        showError(it, R.string.retry, reloadView)
    }

    private val reloadView = View.OnClickListener {
        hideError()
        getRequest()
    }

    private fun getRequest(){
        shimmerView_bookDetail.startShimmer()
        book?.let { bookDetailViewModel.setUp(it) }
    }

    private fun onError(mensaje: String) {
        Toast.makeText(applicationContext, mensaje, Toast.LENGTH_LONG).show()
    }

}