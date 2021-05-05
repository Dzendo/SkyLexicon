package com.dinadurykina.skylexicon.ui.meanings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dinadurykina.skylexicon.databinding.RowItemImageMeaningBinding
import com.dinadurykina.skylexicon.databinding.TextRowItemSkySearchBinding
import com.dinadurykina.skylexicon.network.WordRecycler

/** An adapter
 * The adapter connects your data to the RecyclerView.
 * It adapts the data so that it can be displayed in a ViewHolder.
 * A RecyclerView uses the adapter to figure out how to display the data on the screen.
 * Адаптер подключает ваши данные к RecyclerView.
 * Он адаптирует данные так, чтобы их можно было отображать в формате ViewHolder.
 * A RecyclerView использует адаптер, чтобы выяснить, как отображать данные на экране.
 * Список это URL картинок одной или много List<String>
 */
//<!--Вариант SkySearchListener-->
//class SkySearchAdapter(val clickListener: SkySearchListener):
//<!--Вариант SkySearchViewModel-->
class SkyMeaningImageAdapter(private val skyMeaningsViewModel: SkyMeaningsViewModel):
    ListAdapter<String, SkyImageViewHolder>(SkyImageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkyImageViewHolder {
        return SkyImageViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SkyImageViewHolder, position: Int) {
        val item = getItem(position)
        //<!--Вариант SkySearchListener-->
        //holder.bind(item, clickListener)
        holder.bind(item, skyMeaningsViewModel)
    }
}
class SkyImageViewHolder private constructor(private val binding: RowItemImageMeaningBinding) :
    RecyclerView.ViewHolder(binding.root) {

    //<!--Вариант SkySearchListener-->
    // fun bind(item: WordRecycler, clickListener: SkySearchListener) {
    fun bind(item: String, skyMeaningsViewModel: SkyMeaningsViewModel) {
        binding.imageUrl = item
        //<!--Вариант SkySearchListener-->
        //binding.clickListener = clickListener
        binding.viewModel = skyMeaningsViewModel
    }

    companion object {
        fun from(parent: ViewGroup): SkyImageViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                RowItemImageMeaningBinding.inflate(layoutInflater, parent, false)
            return SkyImageViewHolder(binding)
        }
    }
}

class SkyImageDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem
    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem
}

// Вызывается из XML при нажатии на элемент списка RecyclerView через лямбду
// вариант codelabs через аргумент обратного вызова
//<!--Вариант SkySearchListener-->
/*class SkySearchListener(val clickListener: (sleepId: String) -> Unit) {
    // сюда item пригонит Word на который нажали а отгоним его id
    fun onClick(night: WordRecycler) = clickListener(night.idRus)
}*/


