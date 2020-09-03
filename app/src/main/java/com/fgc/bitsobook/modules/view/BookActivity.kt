package com.fgc.bitsobook.modules.view

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fgc.bitsobook.BitsoBookError
import com.fgc.bitsobook.base.BaseAdapter
import com.fgc.bitsobook.R
import com.fgc.bitsobook.api.models.result.ListPayLoad
import com.fgc.bitsobook.base.BaseActivity
import com.fgc.bitsobook.modules.view.adapter.BookAdapter
import com.fgc.bitsobook.modules.viewModel.BookViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.shimmer_recycler_view.*

class BookActivity : BaseActivity(), BaseAdapter.OnItemClickListener<ListPayLoad>{

    private lateinit var adapter: BookAdapter
    private lateinit var bookViewModel: BookViewModel

    @LayoutRes
    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun setUp() {
        getRequest()
    }

    override fun initVMObservers() {
        super.initVMObservers()
        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        bookViewModel.onGetBookSuccess().observe(this, Observer { onBookRetrieved(it) })
        bookViewModel.onGetBookServiceError().observe(this, Observer { onBookRetrieveError(it) })
        bookViewModel.onError().observe(this, Observer { onError(it) })
    }

    override fun initView() {
        super.initView()
        shimmerView_book.startShimmer()
        adapter = BookAdapter()
        recyclerViewBook.setHasFixedSize(true)
        recyclerViewBook.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        recyclerViewBook.adapter = adapter
        adapter.onItemClickListener = this
    }

    private fun onBookRetrieved(it: List<ListPayLoad>) {
        shimmerView_book.stopShimmer()
        adapter.changeDataSet(it)
    }

    private fun onBookRetrieveError(it: BitsoBookError) {
        Log.e(javaClass.simpleName, it.toString())
        shimmerView_book.stopShimmer()
        showError(it, R.string.retry, reloadView)
    }

    private val reloadView = View.OnClickListener {
        hideError()
        getRequest()
    }

    private fun getRequest(){
        bookViewModel.setUp()
    }

    private fun onError(mensaje: String) {
        Toast.makeText(applicationContext, mensaje, Toast.LENGTH_LONG).show()
    }

    override fun onItemClick(view: View, entity: ListPayLoad, position: Int) {
        //startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(entity.book)))
    }
}