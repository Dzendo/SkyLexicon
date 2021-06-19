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

import androidx.lifecycle.*
import com.dinadurykina.skylexicon.ui.about.SkyHistory

import com.dinadurykina.skylexicon.network.DataItem
import com.dinadurykina.skylexicon.network.ImageUrl
import com.dinadurykina.skylexicon.network.Meaning
import com.dinadurykina.skylexicon.repository.SkyRepository
import com.dinadurykina.skylexicon.ui.Event
import com.dinadurykina.skylexicon.ui.playSound
import kotlinx.coroutines.launch

/**
 * The [ViewModel] that is attached to the [SkyMeaningsFragment].
 */
//class SkyMeaningsViewModel : ViewModel() {
class SkyMeaningsViewModel(
    private val skyRepository : SkyRepository,
    ids:String,
    private val skyHistory: SkyHistory,
    ) : ViewModel() {
    // private val skyRepository = SkyRepository()

    // Вводимое слово связано двухсторонним биндингом с полем
    // наблюдается из фрагмента и при изменении зовется поиск
    val ids: MutableLiveData<String> = MutableLiveData<String>(ids)

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    // Для одного значения слова расшифрованного
    private val _meaning = MutableLiveData<Meaning>()  // Данные для одного значения
    val meaning: LiveData<Meaning>
        get() = _meaning

    // Для списка значений слова расшифрованного
    private val _meanings = MutableLiveData<List<Meaning>>()  // Содержит все данные
    val meanings: LiveData<List<Meaning>>
        get() = _meanings

    private val _dataItem = MutableLiveData<List<DataItem>>()
    val dataItem: LiveData<List<DataItem>>
        get() = _dataItem

    // список адресов картинок для ImageRecyclerView
    private val _imagesListRecycler = MutableLiveData<List<ImageUrl>>()
    val imagesListRecycler: LiveData<List<ImageUrl>>
        get() = _imagesListRecycler

    /**
     * Sets the value of the status LiveData to the Sky API status.
     */
    fun meaningsIds(ids: String) {
        viewModelScope.launch {
            _response.value = "empty"

            try {
                _meanings.value = skyRepository.getSkyMeanings(ids).value

                _dataItem.value = skyRepository.getDataItemMeaningsRecycler(ids).value

                _imagesListRecycler.value = skyRepository.getSkyMeaning0(ids).value?.images

                _meaning.value = skyRepository.getSkyMeaning0(ids).value

                skyHistory.addHistoryMeaning(meaning.value!!)

            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }

    fun onClickSound(imageUrl:String) = playSound(imageUrl)

    fun onClickSimilar(meaningId:Long) { ids.value = meaningId.toString() }

    private val _navigateToSkySearch = MutableLiveData<Event<String?>>(Event(null))
    val navigateToSkySearch: LiveData<Event<String?>>
        get() = _navigateToSkySearch

    fun onSkySearchNavigate(text:String) { _navigateToSkySearch.value = Event(text) }

}
@Suppress("UNCHECKED_CAST")
class SkyMeaningsViewModelFactory (
    private val skyRepository: SkyRepository,
    private val ids: String,
    private val skyHistory: SkyHistory
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (SkyMeaningsViewModel(skyRepository,ids,skyHistory) as T)
}

