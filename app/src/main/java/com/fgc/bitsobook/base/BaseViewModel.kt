package com.fgc.bitsobook.base

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
            compositeDisposable.clear()
        }
        Log.e(javaClass.simpleName, "=============================| onCleared |=============================")
    }

    protected fun Disposable.doDispose() {
        compositeDisposable.add(this)
    }
}