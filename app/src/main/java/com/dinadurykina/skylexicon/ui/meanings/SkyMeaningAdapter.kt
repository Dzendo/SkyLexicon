package com.dinadurykina.skylexicon.ui.meanings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dinadurykina.skylexicon.network.Example
import com.dinadurykina.skylexicon.network.MeaningsWithSimilarTranslation
import com.dinadurykina.skylexicon.network.AlternativeTranslations
import com.dinadurykina.skylexicon.databinding.RowItemExampleMeaningBinding
import com.dinadurykina.skylexicon.databinding.RowItemSimilarMeaningBinding
import com.dinadurykina.skylexicon.databinding.RowItemAlternativeMeaningBinding
import com.dinadurykina.skylexicon.network.DataItem
import com.dinadurykina.skylexicon.ui.DiffCallback

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

class SkyMeaningAdapter(private val skyMeaningsViewModel: SkyMeaningsViewModel) :
    ListAdapter<DataItem, ViewHolder>(DiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return with(DataItem) {
            when (getItem(position)) {
                is DataItem.ExampleItem -> ITEM_VIEW_TYPE_EXAMPLE
                is DataItem.MeaningWithSimilarTranslationItem -> ITEM_VIEW_TYPE_SIMILAR
                is DataItem.AlternativeTranslationsItem -> ITEM_VIEW_TYPE_ALTERNATIVE
            }
        }
    }
    // Стандартный метод RecyclerView  - Создает View строчки-карточки место куда Bind занесет данные
    // Он отвечает за внешний вид строчки RecyclerView: конструирует ее и отдает на высветку
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return with(DataItem) {
            when (viewType) {
                ITEM_VIEW_TYPE_EXAMPLE -> ExampleViewHolder.from(parent)
                ITEM_VIEW_TYPE_SIMILAR -> SimilarViewHolder.from(parent)
                ITEM_VIEW_TYPE_ALTERNATIVE -> AlternativeViewHolder.from(parent)
                else -> throw ClassCastException("Unknown viewType $viewType")
            }
        }
    }

    // Стандартный метод RecyclerView  - заполняет реальные данные факта в поля строчки (ID букву, значения полей)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is ExampleViewHolder -> {
                val item = getItem(position) as DataItem.ExampleItem
                holder.bind(item.example, skyMeaningsViewModel)
            }
            is SimilarViewHolder -> {
                val item = getItem(position) as DataItem.MeaningWithSimilarTranslationItem
                holder.bind(item.meaningWithSimilarTranslation, skyMeaningsViewModel)
            }
            is AlternativeViewHolder -> {
                val item = getItem(position) as DataItem.AlternativeTranslationsItem
                holder.bind(item.alternativeTranslations, skyMeaningsViewModel)
            }
        }
    }
}
// три класса для каждого вида данных - создатели - держатели места - вида
class ExampleViewHolder(private val binding: RowItemExampleMeaningBinding) :
            ViewHolder(binding.root) {

    fun bind(item: Example, skyMeaningsViewModel: SkyMeaningsViewModel) {
        binding.example = item
        binding.viewmodel = skyMeaningsViewModel
    }
    companion object {
        fun from(parent: ViewGroup): ExampleViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                RowItemExampleMeaningBinding.inflate(layoutInflater, parent, false)
            return ExampleViewHolder(binding)
        }
    }
}

class SimilarViewHolder(private val binding: RowItemSimilarMeaningBinding) :
            ViewHolder(binding.root) {

    fun bind(item: MeaningsWithSimilarTranslation, skyMeaningsViewModel: SkyMeaningsViewModel) {
        binding.similar = item
        binding.viewmodel = skyMeaningsViewModel
    }
    companion object {
        fun from(parent: ViewGroup): SimilarViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                RowItemSimilarMeaningBinding.inflate(layoutInflater, parent, false)
            return SimilarViewHolder(binding)
        }
    }
}

class AlternativeViewHolder(private val binding: RowItemAlternativeMeaningBinding) :
            ViewHolder(binding.root) {

    fun bind(item: AlternativeTranslations, skyMeaningsViewModel: SkyMeaningsViewModel) {
        binding.alternative = item
        binding.viewmodel = skyMeaningsViewModel
    }
    companion object {
        fun from(parent: ViewGroup): AlternativeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                RowItemAlternativeMeaningBinding.inflate(layoutInflater, parent, false)
            return AlternativeViewHolder(binding)
        }
    }
}
