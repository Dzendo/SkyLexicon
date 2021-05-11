package com.dinadurykina.skylexicon.ui.meanings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dinadurykina.skylexicon.databinding.RowItemImageMeaningBinding
import com.dinadurykina.skylexicon.network.ImageUrl

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

class SkyMeaningImageAdapter(private val skyMeaningsViewModel: SkyMeaningsViewModel):
    ListAdapter<ImageUrl, ImageViewHolder>(ImageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, skyMeaningsViewModel)
    }
}

class ImageViewHolder private constructor(private val binding: RowItemImageMeaningBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ImageUrl, skyMeaningsViewModel: SkyMeaningsViewModel) {
        binding.image = item
        binding.viewmodel = skyMeaningsViewModel
    }
    companion object {
        fun from(parent: ViewGroup): ImageViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                RowItemImageMeaningBinding.inflate(layoutInflater, parent, false)
            return ImageViewHolder(binding)
        }
    }

}

class ImageDiffCallback : DiffUtil.ItemCallback<ImageUrl>() {
    override fun areItemsTheSame(oldItem: ImageUrl, newItem: ImageUrl): Boolean =
        oldItem.url == newItem.url
    override fun areContentsTheSame(oldItem: ImageUrl, newItem: ImageUrl): Boolean =
        oldItem == newItem
}

