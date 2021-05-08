/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.dinadurykina.skylexicon.ui

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dinadurykina.skylexicon.R
import com.dinadurykina.skylexicon.network.ImageUrl
import com.dinadurykina.skylexicon.network.WordRecycler
import com.dinadurykina.skylexicon.ui.meanings.SkyMeaningAdapter
import com.dinadurykina.skylexicon.ui.meanings.SkyMeaningImageAdapter
import com.dinadurykina.skylexicon.ui.search.SkySearchAdapter

// 11.2.1 В BindingAdapters создать BindingAdapter для преобразования imgUrl к URI со схемой HTTPS.
// Внутри адаптера используйте Glide для загрузки изображения, отобразите его в imgView:
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            // Добавьте простые изображения загрузки и ошибок
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}
// Пример не используется
@BindingAdapter(value = ["setImageUrl"])
fun ImageView.bindImageUrl(url: String?) {
    if (url != null && url.isNotBlank()) {
        val uri = url.toUri().buildUpon().scheme("https").build()
        Glide.with(this)
          //.load(url)
            .load(uri)
            .into(this)
    }
}

// RequestOptions к Glide вызову, чтобы добавить заполнитель,
// который отображает изображение во время загрузки изображения,
// и изображение ошибки, если его невозможно получить.

// bindRecyclerView связывающий адаптер для listData, и он звонить submitList()на SkySearchAdapter:
// используется в layout\fragment_sky_search.xml
// для привязки / наблюдения за живыми данными viewModel.wordsListRecycler
@BindingAdapter("wordListData")
fun bindWordRecyclerView(recyclerView: RecyclerView, data: List<WordRecycler>?) {
    (recyclerView.adapter as SkySearchAdapter).submitList(data)
}
@BindingAdapter("imageListData")
fun bindImageRecyclerView(recyclerView: RecyclerView, data: List<ImageUrl>?) {
    (recyclerView.adapter as SkyMeaningImageAdapter).submitList(data)
}
@BindingAdapter("meaningListData")
fun bindMeaningRecyclerView(recyclerView: RecyclerView, data: List<String>?) {
    (recyclerView.adapter as SkyMeaningAdapter).submitList(data)
}

// Пример не используется
@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
    this.run {
        this.setHasFixedSize(true)
        this.adapter = adapter
    }
}

/*
// 13.4 добавьте адаптер привязки для отображения MarsApiStatus в ImageView
// и установите видимость представления в зависимости от значения состояния:
@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: MarsApiStatus?) =
    when (status) {
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MarsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        else -> statusImageView.visibility = View.INVISIBLE
    }
*/