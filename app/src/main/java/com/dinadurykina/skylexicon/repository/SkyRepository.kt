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

    // основная функция поиска перевода слова
    suspend fun getSkySearch(slovo: String): LiveData<List<Word>> {
        val rezult: List<Word>
        withContext(Dispatchers.IO) {
            rezult = SkyApi.retrofitService.getSearch(slovo)//.await()  // await говорит что это корутинный  Deferred
        }
        return MutableLiveData(rezult)
    }

    suspend fun getSkySearchResult(slovo: String): LiveData<Result<List<Word>>> {
        val rezult: List<Word>
        withContext(Dispatchers.IO) {
            rezult = SkyApi.retrofitService.getSearch(slovo)//.await()  // await говорит что это корутинный  Deferred
        }
        return (MutableLiveData(Result.success(rezult)))
    }
    suspend fun getSkySearch0(slovo: String): Word? = getSkySearch(slovo).value?.get(0)

    // основная функция поиска значения слова
    suspend fun getSkyMeanings(ids: String): LiveData<List<Meaning>> {
        val rezult: List<Meaning>
        withContext(Dispatchers.IO) {
            rezult = SkyApi.retrofitService.getMeanings(ids)//.await()  // await говорит что это корутинный  Deferred
        }
        return MutableLiveData(rezult)
    }

    suspend fun getSkyMeaning0(ids: String): LiveData<Meaning> {
         return MutableLiveData(getSkyMeanings(ids).value?.get(0))
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

        return MutableLiveData(mutableListRecycler)
    }

    suspend fun getDataItemMeaningsRecycler(ids: String): LiveData<List<DataItem>> {
        val oneMeanig = getSkyMeaning0(ids).value!!

        val example = oneMeanig.examples
            .map { DataItem.ExampleItem(it) }
        val similar = oneMeanig.meaningsWithSimilarTranslation
            .map { DataItem.MeaningWithSimilarTranslationItem(it) }
        val alternative = oneMeanig.alternativeTranslations
            .map { DataItem.AlternativeTranslationsItem(it) }
        return MutableLiveData(similar + example + alternative)
    }
}


