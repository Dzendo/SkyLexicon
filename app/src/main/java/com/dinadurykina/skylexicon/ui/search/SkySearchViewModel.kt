/*
 * Copyright 2019, The Android Open Source Project
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

package com.dinadurykina.skylexicon.ui.search

import android.view.View
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinadurykina.skylexicon.network.WordRecycler
import com.dinadurykina.skylexicon.repository.SkyRepository
import kotlinx.coroutines.launch

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class SkySearchViewModel(val slovo:String) : ViewModel() {
    private val skyRepository = SkyRepository()
   // Для Json нерасшифрованного (отладка)
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    // Основной список для RecyclerView
    private val _wordsListRecycler = MutableLiveData<List<WordRecycler>>()
        val wordsListRecycler: LiveData<List<WordRecycler>>
            get() = _wordsListRecycler

    init {
        searchSlovo(slovo)
    }
    private val _navigateToSkyMeanings = MutableLiveData<String?>()
    val navigateToSkyMeanings
        get() = _navigateToSkyMeanings

    private val _showImage = MutableLiveData<String?>()
    val showImage
        get() = _showImage

    private val _listenSound = MutableLiveData<String?>()
    val listenSound
        get() = _listenSound

    // для варианта codelabs SkySearchListener и Вариант SkySearchViewModel
    fun onSkySearchClicked(id:String) {
        _navigateToSkyMeanings.value = id
    }
    fun onSkyMeaningsNavigated() {
        _navigateToSkyMeanings.value = null
    }

     fun onSlovoClicked(view:View) {
         val slovo = (view as EditText).text.toString()
         searchSlovo(slovo)
     }
    fun searchSlovo(slovo:String) {
        viewModelScope.launch {
            _response.value = "good"

            try {
                val skyResultRecycler = skyRepository.getSkySearchRecycler(slovo)
                _wordsListRecycler.value = skyResultRecycler.value
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
     }

    fun onSkyImageClicked(imageUri:String) {
        _showImage.value = imageUri
    }
    fun onShowImageNavigated() {
        _showImage.value = null
    }
    fun onListenSoundClicked(imageUrl:String) {
        _listenSound.value = imageUrl
    }
    fun onSkySoundNavigated() {
        _listenSound.value = null
    }
}
