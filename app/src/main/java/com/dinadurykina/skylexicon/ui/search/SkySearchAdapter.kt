package com.dinadurykina.skylexicon.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dinadurykina.skylexicon.databinding.RowItemSkySearchBinding
import com.dinadurykina.skylexicon.network.WordRecycler

/** An adapter
 * The adapter connects your data to the RecyclerView.
 * It adapts the data so that it can be displayed in a ViewHolder.
 * A RecyclerView uses the adapter to figure out how to display the data on the screen.
 * Адаптер подключает ваши данные к RecyclerView.
 * Он адаптирует данные так, чтобы их можно было отображать в формате ViewHolder.
 * A RecyclerView использует адаптер, чтобы выяснить, как отображать данные на экране.
 */
//<!--Вариант SkySearchListener-->
//class SkySearchAdapter(val clickListener: SkySearchListener):
//<!--Вариант SkySearchViewModel-->
class SkySearchAdapter(private val skySearchViewModel: SkySearchViewModel):
    ListAdapter<WordRecycler, WordViewHolder>(SkyDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val item = getItem(position)
        //<!--Вариант SkySearchListener-->
        //holder.bind(item, clickListener)
        holder.bind(item, skySearchViewModel)
    }
}
class WordViewHolder private constructor(private val binding: RowItemSkySearchBinding) :
    RecyclerView.ViewHolder(binding.root) {

    //<!--Вариант SkySearchListener-->
    // fun bind(item: WordRecycler, clickListener: SkySearchListener) {
    fun bind(item: WordRecycler, skySearchViewModel: SkySearchViewModel) {
        binding.word = item
        //<!--Вариант SkySearchListener-->
        //binding.clickListener = clickListener
        binding.viewmodel = skySearchViewModel
    }

    companion object {
        fun from(parent: ViewGroup): WordViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                RowItemSkySearchBinding.inflate(layoutInflater, parent, false)
            return WordViewHolder(binding)
        }
    }
}

class SkyDiffCallback : DiffUtil.ItemCallback<WordRecycler>() {
    override fun areItemsTheSame(oldItem: WordRecycler, newItem: WordRecycler): Boolean =
        oldItem.idEng == newItem.idEng
    override fun areContentsTheSame(oldItem: WordRecycler, newItem: WordRecycler): Boolean =
        oldItem == newItem
}

// Вызывается из XML при нажатии на элемент списка RecyclerView через лямбду
// вариант codelabs через аргумент обратного вызова
//<!--Вариант SkySearchListener-->
/*class SkySearchListener(val clickListener: (sleepId: String) -> Unit) {
    // сюда item пригонит Word на который нажали а отгоним его id
    fun onClick(night: WordRecycler) = clickListener(night.idRus)
}*/


