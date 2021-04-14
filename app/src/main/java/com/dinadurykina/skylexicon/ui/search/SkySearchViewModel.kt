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
import com.dinadurykina.skylexicon.network.Word
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

    // Для одного найденного слова расшифрованного
    private val _word = MutableLiveData<Word>()  // Данные для одного изображения
    val word: LiveData<Word>
        get() = _word
    // Для списка найденных слов расшифрованного
    private val _words = MutableLiveData<List<Word>>()  // Содержит все данные
    val words: LiveData<List<Word>>
        get() = _words

    private lateinit var oneWord0: Word

    // Чтобы сообщить, RecyclerView когда данные, которые он отображает, изменились,
    // добавьте настраиваемый установщик к data переменной,
    // которая находится в верхней части SleepNightAdapter класса.
    // В установщике задайте data новое значение, затем вызовите, notifyDataSetChanged()
    // чтобы запустить перерисовку списка с новыми данными.
    /*
    var data =  listOf<SleepNight>()
   set(value) {
       field = value
       notifyDataSetChanged()
   }
     */

    val meanings02Recycler: MutableList<String?> = arrayListOf()
    val meanings02: MutableList<String?> = arrayListOf()
    val meanings20: MutableList<String?> = arrayListOf()
    val meanings21: MutableList<String?> = arrayListOf()
    // Для одного найденного слова расшифрованного
    private val _wordRecycler = MutableLiveData<WordRecycler>()  // Данные для одного изображения
    val wordRecycler: LiveData<WordRecycler>
        get() = _wordRecycler
    // Для списка найденных слов расшифрованного
    private val _wordsRecycler = MutableLiveData<List<WordRecycler>>()  // Содержит все данные
    val wordsRecycler: LiveData<List<WordRecycler>>
        get() = _wordsRecycler

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

     fun onSlovoClicked(view:View) {
         val slovo = (view as EditText).text.toString()
         searchSlovo(slovo)
     }
    fun searchSlovo(slovo:String) {
        viewModelScope.launch {
            _response.value = "empty"
            meanings02Recycler.clear()
            meanings02.clear()
            meanings20.clear()
            meanings21.clear()
            try {
                val skyResultRecycler = skyRepository.getSkySearchRecycler(slovo)
                val skyResult = skyRepository.getSkySearch(slovo)
                _response.value = "Search ${skyResult.value?.size} : \n ${skyResult.value} \n End Sky Search \n "
                _words.value = skyResult.value
                _wordsRecycler.value = skyResultRecycler.value

                // Формирую данные - лист строк - для Recycler методом склейки всех трех списков
                // Пока он просто перечень строк
                if (skyResult.value?.size?:0 > 0) {
                    _word.value = skyResult.value?.get(0)
                    oneWord0 = skyResult.value?.get(0)!!
                    meanings02Recycler.addAll(oneWord0.meanings
                        .map{it.id.toString() + " " + it.translation.text + "," +
                                (it.translation.note ?: "")
                        })
                    meanings02Recycler.addAll(words.value!!
                        .map{it.text + " " + it.meanings[0].id.toString() + " " + it.meanings[0].translation.text + "," +
                                (it.meanings[0].translation.note ?: "")
                        })
                }
                skyResultRecycler.value?.let {
                    meanings02Recycler.addAll(
                        skyResultRecycler.value!!
                            .map{it.idRus.toString() + " " + it.textEng + " --> " +
                                    it.textRus + "," +
                                    (it.note ?: "")
                            })
                }


                if (skyResult.value?.size?:0 > 0) {
                    _word.value = skyResult.value?.get(0)
                    oneWord0 = skyResult.value?.get(0)!!
                    meanings02.addAll(oneWord0.meanings
                        .map{it.id.toString() + " " + it.translation.text + "," +
                                (it.translation.note ?: "")
                        })
                    meanings20.addAll(words.value!!
                        .map{it.text + " " + it.meanings[0].id.toString() + " " + it.meanings[0].translation.text + "," +
                                (it.meanings[0].translation.note ?: "")
                        })
                }

                    skyResultRecycler.value?.let {
                        meanings21.addAll(
                            skyResultRecycler.value!!
                                .map{it.idRus.toString() + " " + it.textEng + " --> " +
                                        it.textRus + "," +
                                        (it.note ?: "")
                                })
                    }


            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
            refreshTrue()
        }
     }
}
