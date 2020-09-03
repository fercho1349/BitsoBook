package com.fgc.bitsobook.extensionFunctions

import android.view.View
import android.widget.TextView
import java.text.NumberFormat
import java.util.*

fun TextView.setAmount(amount: Int?, fallback: String) {
    this.text = if (amount != null) NumberFormat.getCurrencyInstance(Locale.US).format(amount) else fallback
}

fun TextView.setText(text: String?, fallback: String) {
    this.text = if (text.isNullOrEmpty()) fallback else text
}

fun TextView.setVisibility(show: Boolean) {
    this.visibility = if (show) View.VISIBLE else View.GONE
}