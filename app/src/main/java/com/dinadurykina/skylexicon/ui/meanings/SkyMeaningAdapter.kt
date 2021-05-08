package com.dinadurykina.skylexicon.ui.meanings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dinadurykina.skylexicon.databinding.RowItemSkyMeaningBinding

/** An adapter
 * The adapter connects your data to the RecyclerView.
 * It adapts the data so that it can be displayed in a ViewHolder.
 * A RecyclerView uses the adapter to figure out how to display the data on the screen.
 * Адаптер подключает ваши данные к RecyclerView.
 * Он адаптирует данные так, чтобы их можно было отображать в формате ViewHolder.
 * A RecyclerView использует адаптер, чтобы выяснить, как отображать данные на экране.
 * Список это URL картинок одной или много List<String>
 * https://medium.com/swlh/android-recyclerview-with-data-binding-and-coroutine-3192097a0496
 * https://medium.com/@ericampire
 * https://ziginsider.github.io/Multiple_Row_Types_In_Recyclerview/
 * https://github.com/ziginsider/MultipleRowTypesInRecyclerViewDemo.git
 */
//<!--Вариант SkySearchListener-->
//class SkySearchAdapter(val clickListener: SkySearchListener):
//<!--Вариант SkySearchViewModel-->
class SkyMeaningAdapter(private val skyMeaningsViewModel: SkyMeaningsViewModel):
    ListAdapter<String, MeaningViewHolder>(MeaningDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningViewHolder {
        return MeaningViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MeaningViewHolder, position: Int) {
        val item = getItem(position)
        //<!--Вариант SkySearchListener-->
        //holder.bind(item, clickListener)
        holder.bind(item, skyMeaningsViewModel)
    }
}
class MeaningViewHolder(private val binding: RowItemSkyMeaningBinding) :
    RecyclerView.ViewHolder(binding.root) {

    //<!--Вариант SkySearchListener-->
    // fun bind(item: WordRecycler, clickListener: SkySearchListener) {
    fun bind(item: String, skyMeaningsViewModel: SkyMeaningsViewModel) {
        binding.dataitem = item
        //<!--Вариант SkySearchListener-->
        //binding.clickListener = clickListener
        binding.viewmodel = skyMeaningsViewModel
        binding.meaning = skyMeaningsViewModel.meaning.value
    }

    companion object {
        fun from(parent: ViewGroup): MeaningViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                RowItemSkyMeaningBinding.inflate(layoutInflater, parent, false)
            return MeaningViewHolder(binding)
        }
    }

}

class MeaningDiffCallback : DiffUtil.ItemCallback<String>() {
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


