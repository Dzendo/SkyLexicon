/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dinadurykina.skylexicon.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dinadurykina.skylexicon.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository module for handling data operations.
 * Модуль репозитория для обработки операций с данными.
 * вызывается из всех ViewModels сам вызывает SkyApiService функции
 */

class SkyRepository {
    // Для списка найденных слов расшифрованного
    private val _listWord = MutableLiveData<List<Word>>()  // Содержит все данные
      val listWord: LiveData<List<Word>>
          get() = _listWord

    // Содержит все данные для перевода на первом экране - поиск-перевод
    private val _listWordRecycler = MutableLiveData<List<WordRecycler>>()  // Содержит все данные
      val listWordRecycler: LiveData<List<WordRecycler>>
          get() = _listWordRecycler

    // основная функция поиска перевода слова
    suspend fun getSkySearch(slovo: String): LiveData<List<Word>> {
        val rezult: List<Word>
        withContext(Dispatchers.IO) {
            rezult = SkyApi.retrofitService.getSearch(slovo)
        }
        _listWord.value = rezult
        return listWord
    }

    // Содержит все данные значения слова
    private val _listMeaning = MutableLiveData<List<Meaning>>()
    val listMeaning: LiveData<List<Meaning>>
        get() = _listMeaning

    // основная функция поиска значения слова
    suspend fun getSkyMeanings(ids: String): LiveData<List<Meaning>> {
        val rezult: List<Meaning>
        withContext(Dispatchers.IO) {
            rezult = SkyApi.retrofitService.getMeanings(ids)
        }
        _listMeaning.value = rezult
        _meaning0.value = rezult[0]
        return listMeaning
    }

    // только первое значение под номером 0
    private val _meaning0 = MutableLiveData<Meaning>()  // Содержит все данные
    val meaning0: LiveData<Meaning>
        get() = _meaning0

    suspend fun getSkyMeaning0(ids: String): LiveData<Meaning> {  // = SkyApi.retrofitService.getMeanings(ids)
        _meaning0.value = getSkyMeanings(ids).value?.get(0)
        return meaning0
    }

    // обращается к API находит слово и заполняет массив для Recycler первого экрана
    suspend fun getSkySearchRecycler(slovo: String): LiveData<List<WordRecycler>> {
        val mutableListRecycler: MutableList<WordRecycler> = arrayListOf()
        val result = getSkySearch(slovo)

        for (word: Word in result.value!!)
            for (meaning2: Meaning2 in word.meanings) {
                mutableListRecycler.add(WordRecycler(
                    idEng = word.id,
                    textEng = word.text,
                    idRus = meaning2.id,
                    partOfSpeechCode = meaning2.partOfSpeechCode,
                    textRus = meaning2.translation.text,
                    note = meaning2.translation.note,
                    previewUrl = meaning2.previewUrl,
                    imageUrl = meaning2.imageUrl,
                    transcription = meaning2.transcription,
                    soundUrl = meaning2.soundUrl
                ))
            }
        _listWordRecycler.value = mutableListRecycler
        return listWordRecycler
    }
}


