package com.fgc.bitsobook.modules.view.holder

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import com.fgc.bitsobook.base.BaseHolder
import kotlinx.android.synthetic.main.item_book.view.*

class BookHolder (itemView: View) : BaseHolder(itemView) {

    val bookTitleOutputText = itemView.bookTitleOutputText as AppCompatTextView
    val bookPriceOutputText = itemView.bookPriceOutputText as AppCompatTextView
    val cardview_book = itemView.cardview_book as CardView
}