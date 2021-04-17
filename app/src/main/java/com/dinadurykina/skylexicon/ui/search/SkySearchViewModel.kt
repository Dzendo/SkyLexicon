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

    private val _wordsListRecycler = MutableLiveData<List<WordRecycler>>()
        val wordsListRecycler: LiveData<List<WordRecycler>>
            get() = _wordsListRecycler

    var ids:String? = null

    init {
        searchSlovo(slovo)
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

                ids = wordsListRecycler.value?.get(0)?.idRus

            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
     }
    fun onWordClicked(view:View) {
        val slovo = (view as EditText).text.toString()
        searchSlovo(slovo)
    }
}
