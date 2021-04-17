package com.dinadurykina.skylexicon.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dinadurykina.skylexicon.databinding.TextRowItemSkySearchBinding
import com.dinadurykina.skylexicon.network.WordRecycler

/** An adapter
 * The adapter connects your data to the RecyclerView.
 * It adapts the data so that it can be displayed in a ViewHolder.
 * A RecyclerView uses the adapter to figure out how to display the data on the screen.
 * Адаптер подключает ваши данные к RecyclerView.
 * Он адаптирует данные так, чтобы их можно было отображать в формате ViewHolder.
 * A RecyclerView использует адаптер, чтобы выяснить, как отображать данные на экране.
 */
class SkySearchAdapter: ListAdapter<WordRecycler, SkySearchAdapter.ViewHolder>(SkyDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: TextRowItemSkySearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WordRecycler) {
            binding.word = item

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    TextRowItemSkySearchBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class SkyDiffCallback : DiffUtil.ItemCallback<WordRecycler>() {
    override fun areItemsTheSame(oldItem: WordRecycler, newItem: WordRecycler): Boolean =
        oldItem.idEng == newItem.idEng
    override fun areContentsTheSame(oldItem: WordRecycler, newItem: WordRecycler): Boolean =
        oldItem == newItem
}


