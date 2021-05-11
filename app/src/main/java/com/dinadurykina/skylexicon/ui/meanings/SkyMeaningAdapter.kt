package com.dinadurykina.skylexicon.ui.meanings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dinadurykina.skylexicon.databinding.RowItemAlternativeMeaningBinding
import com.dinadurykina.skylexicon.network.Example
import com.dinadurykina.skylexicon.network.MeaningsWithSimilarTranslation
import com.dinadurykina.skylexicon.databinding.RowItemExampleMeaningBinding
import com.dinadurykina.skylexicon.databinding.RowItemSimilarMeaningBinding
import com.dinadurykina.skylexicon.network.AlternativeTranslations

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

private const val ITEM_VIEW_TYPE_EXAMPLE = 1
private const val ITEM_VIEW_TYPE_SIMILAR = 2
private const val ITEM_VIEW_TYPE_ALTERNATIVE = 3

class SkyMeaningAdapter(private val skyMeaningsViewModel: SkyMeaningsViewModel) :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(MeaningDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.ExampleItem -> ITEM_VIEW_TYPE_EXAMPLE
            is DataItem.MeaningWithSimilarTranslationItem -> ITEM_VIEW_TYPE_SIMILAR
            is DataItem.AlternativeTranslationsItem -> ITEM_VIEW_TYPE_ALTERNATIVE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_EXAMPLE -> ExampleViewHolder.from(parent)
            ITEM_VIEW_TYPE_SIMILAR -> SimilarViewHolder.from(parent)
            ITEM_VIEW_TYPE_ALTERNATIVE -> AlternativeViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
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

class ExampleViewHolder(private val binding: RowItemExampleMeaningBinding) :
    RecyclerView.ViewHolder(binding.root) {

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
    RecyclerView.ViewHolder(binding.root) {

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
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: AlternativeTranslations, skyMeaningsViewModel: SkyMeaningsViewModel) {
        binding.alternative = item
        //<!--Вариант SkySearchListener-->
        //binding.clickListener = clickListener
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

class MeaningDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean =
        oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean =
        oldItem == newItem
}

// DataItem который представляет элемент данных разных типов
sealed class DataItem {
    abstract val id: String
    data class ExampleItem(val example: Example) : DataItem() {
        override val id = example.text
    }
    data class MeaningWithSimilarTranslationItem(
        val meaningWithSimilarTranslation: MeaningsWithSimilarTranslation
    ) : DataItem() {
        override val id = meaningWithSimilarTranslation.meaningId.toString()
    }
    data class AlternativeTranslationsItem(
        val alternativeTranslations: AlternativeTranslations
    ) : DataItem() {
        override val id = alternativeTranslations.text
    }
}



