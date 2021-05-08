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

package com.dinadurykina.skylexicon.ui.meanings

import android.view.View
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinadurykina.skylexicon.network.ImageUrl
import com.dinadurykina.skylexicon.network.Meaning
import com.dinadurykina.skylexicon.repository.SkyRepository
import kotlinx.coroutines.launch

/**
 * The [ViewModel] that is attached to the [SkyMeaningsFragment].
 */
class SkyMeaningsViewModel : ViewModel() {
     private val skyRepository = SkyRepository()
    // Для Json нерасшифрованного (отладка)
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    var ids: String = "132398"

    // Для одного значения слова расшифрованного
    private val _meaning = MutableLiveData<Meaning>()  // Данные для одного значения
    val meaning: LiveData<Meaning>
        get() = _meaning

    // Для списка значений слова расшифрованного
    private val _meanings = MutableLiveData<List<Meaning>>()  // Содержит все данные
    val meanings: LiveData<List<Meaning>>
        get() = _meanings

    private lateinit var oneMeanig: Meaning

    //val dataItem : MutableList<String?> =  arrayListOf()

    private val _dataItem = MutableLiveData<List<String>>()
    val dataItem: LiveData<List<String>>
        get() = _dataItem

    val alternativeTranslations: MutableList<String?> =  arrayListOf()

    // список адресов картинок для ImageRecyclerView
    private val _imagesListRecycler = MutableLiveData<List<ImageUrl>>()
    val imagesListRecycler: LiveData<List<ImageUrl>>
        get() = _imagesListRecycler

    // Объявляю живой флажок, надо ли обновить адапреты ListView из фрагмента, (по умолчанию нет - null)
    private val _refresh: MutableLiveData<Boolean?> = MutableLiveData<Boolean?>(null)
    val refresh: LiveData<Boolean?>
        get() = _refresh
    private fun refreshTrue() {
        _refresh.value = true
    }
    fun refreshNull() {
        _refresh.value = null
    }

    private val _listenSound = MutableLiveData<String?>()
    val listenSound
        get() = _listenSound

    init {
   //     meaningsIds(ids)
    }
    /**
     * Sets the value of the status LiveData to the Sky API status.
     */
     fun onIdsClicked(view:View) {
        val ids = (view as EditText).text.toString()
        meaningsIds(ids)
    }
     fun meaningsIds(ids: String) {
         viewModelScope.launch {
             _response.value = "empty"

             //dataItem.clear()
             alternativeTranslations.clear()

             try {
                 val skyResult = skyRepository.getSkyMeanings(ids)
                 _response.value = "Meanings ${skyResult.value?.size} : \n ${skyResult.value} \n End Sky Meanings  \n"
                 _meanings.value = skyResult.value
                 if (skyResult.value?.size?:0 > 0) {
                     _meaning.value = skyResult.value?.get(0)
                     oneMeanig = skyResult.value?.get(0)!!

                     val dataItem : MutableList<String> =  arrayListOf()
                     dataItem.addAll( oneMeanig.examples.map{it.text})
                     dataItem.addAll( oneMeanig.meaningsWithSimilarTranslation
                         .map{it.meaningId.toString() + " " + it.translation.text + " " +
                                 (it.translation.note ?: "") + " " +
                                 it.partOfSpeechAbbreviation + it.frequencyPercent + "%"
                         })
                     _dataItem.value = dataItem

                     alternativeTranslations.addAll( oneMeanig.alternativeTranslations
                         .map{it.text + " " + it.translation.text + " " +
                                 (it.translation.note ?: "")
                         })

                     _imagesListRecycler.value = oneMeanig.images +
                            ImageUrl("//d2zkmv5t5kao9.cloudfront.net/images/b905a618b56c721ce683164259ac02c4.jpeg?w=200&h=150&q=50") +
                            ImageUrl("//d2zkmv5t5kao9.cloudfront.net/images/19b11a8848201a3250ebc16339329a79.jpeg?w=200&h=150&q=50") +
                            ImageUrl( "//d2zkmv5t5kao9.cloudfront.net/images/1f4efec895bcc55352e9a47575b624d3.jpeg?w=200&h=150&q=50")

                 }
             } catch (e: Exception) {
                 _response.value = "Failure: ${e.message}"
             }
             refreshTrue()    // notifyDataSetChanged()
         }
    }
    fun onListenSoundClicked(soundUrl:String) {
        _listenSound.value = soundUrl
    }
    fun onSkySoundNavigated() {
        _listenSound.value = null
    }
}
