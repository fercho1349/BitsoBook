package com.fgc.bitsobook.base

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.fgc.bitsobook.BitsoBookError
import com.fgc.bitsobook.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_base.*

abstract class BaseActivity : AppCompatActivity(){

    private var snackbar: Snackbar? = null

    @LayoutRes
    abstract fun getLayoutResource(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        layoutInflater.inflate(getLayoutResource(), bodyContent, true)
        initVMObservers()
        initView()
        setUp()
    }

    protected open fun setUp() {}

    protected open fun initVMObservers() {}

    protected open fun initView() {}

    fun showError(error: BitsoBookError, @StringRes actionTitle: Int, callback: View.OnClickListener){
        snackbar = Snackbar.make(findViewById(android.R.id.content), getText(error.errorType), Snackbar.LENGTH_INDEFINITE)
            .setAction(getText(actionTitle), callback)
            .setActionTextColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
        snackbar?.view?.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)?.maxLines = 4
        snackbar?.show()
    }

    fun hideError(){
        snackbar?.dismiss()
        snackbar = null
    }

}