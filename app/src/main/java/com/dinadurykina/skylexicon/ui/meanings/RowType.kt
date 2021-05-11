package com.dinadurykina.skylexicon.ui.meanings

import androidx.recyclerview.widget.RecyclerView.ViewHolder

interface RowType {
    val itemViewType: Int
    fun onBindViewHolder(viewHolder: ViewHolder?)

    companion object {
        const val ITEM_VIEW_ROW_TYPE_EXAMPLE = 1
        const val ITEM_VIEW_ROW_TYPE_SIMILAR = 2
        const val ITEM_VIEW_ROW_TYPE_ALTERNATIVE = 3
    }
}