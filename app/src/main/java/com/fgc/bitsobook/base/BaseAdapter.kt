package com.fgc.bitsobook.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<M, VH : BaseHolder> : RecyclerView.Adapter<VH>() {

    interface OnItemClickListener<M> {
        fun onItemClick(view: View, entity: M, position: Int)
    }

    interface OnChangeDataSetListener {
        fun onChange()
    }

    lateinit var context: Context

    var dataSet: List<M>? = null
    var onItemClickListener: OnItemClickListener<M>? = null
    var onChangeDataSetListener: OnChangeDataSetListener? = null

    @LayoutRes
    abstract fun getRowViewResourceId(): Int

    abstract fun createHolder(cardView: View, viewType: Int): VH
    abstract fun bindViewHolder(holder: VH, item: M, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        context = parent.context
        val cardView = LayoutInflater.from(parent.context).inflate(getRowViewResourceId(), parent, false)
        return createHolder(cardView, viewType)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = dataSet?.get(holder.adapterPosition)
        if (item != null) {
            bindViewHolderDefaultState(holder)
            bindViewHolder(holder, item, position)
            holder.itemView.setOnClickListener { onItemClickListener?.onItemClick(holder.itemView, item, holder.adapterPosition) }
        }
    }

    abstract fun bindViewHolderDefaultState(holder: VH)

    open fun changeDataSet(dataSet: List<M>?) {
        this.dataSet = dataSet
        notifyDataSetChanged()
        onChangeDataSetListener?.onChange()
    }
}