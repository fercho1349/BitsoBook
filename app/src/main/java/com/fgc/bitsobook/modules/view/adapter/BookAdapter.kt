package com.fgc.bitsobook.modules.view.adapter

import android.graphics.Color
import android.view.View
import com.fgc.bitsobook.R
import com.fgc.bitsobook.api.models.result.ListPayLoad
import com.fgc.bitsobook.base.BaseAdapter
import com.fgc.bitsobook.extensionFunctions.setText
import com.fgc.bitsobook.modules.view.holder.BookHolder

class BookAdapter  : BaseAdapter<ListPayLoad, BookHolder>() {

    override fun getRowViewResourceId(): Int = R.layout.item_book
    override fun createHolder(cardView: View, viewType: Int): BookHolder = BookHolder(cardView)
    override fun getItemCount(): Int = if (dataSet.isNullOrEmpty()) 0 else dataSet!!.size

    override fun bindViewHolder(holder: BookHolder, item: ListPayLoad, position: Int) {
        holder.bookTitleOutputText.setText(item.book, "")
        holder.bookPriceOutputText.setText("$"+item.maximum_price, "")
        if(position %2 == 1) {
            holder.cardview_book.setCardBackgroundColor(Color.parseColor("#03DAC5"))
        } else {
            holder.cardview_book.setCardBackgroundColor(Color.parseColor("#3700B3"))
        }
    }

    override fun bindViewHolderDefaultState(holder: BookHolder) {
        holder.bookTitleOutputText.text = ""
        holder.bookPriceOutputText.text = ""
    }
}