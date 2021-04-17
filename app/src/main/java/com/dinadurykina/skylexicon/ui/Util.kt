package com.dinadurykina.skylexicon.ui

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)
// Существует как простейшая заглушка для первого вызова
// RecyclerView без него не может просто подставить textView
// ViewHolder дает что именно где и как надо отображать RecyclerView
// еще через его методы доступ ко всей информации о элементе отображения
// номер, позиция, итд