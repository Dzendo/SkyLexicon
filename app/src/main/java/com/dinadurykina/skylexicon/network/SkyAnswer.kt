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

package com.dinadurykina.skylexicon.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

// преобразовать класс в класс данных Kotlin со свойствами, которые соответствуют полям ответа JSON
// Moshi анализирует эти данные JSON и преобразует их в объекты Kotlin.
// Для этого ему необходим класс данных Kotlin для хранения проанализированных результатов,
// поэтому следующим шагом будет создание этого класса:
// преобразовать класс в класс данных Kotlin со свойствами, которые соответствуют полям ответа JSON
// Добавить : @Parcelize  и Parcelable  (experimental = true)
// добавление подробного экрана
// сделайте класс доступным, расширив его Parcelable и добавив @Parcelize аннотацию:

@Parcelize
data class Word(  // в ответ идет List из 15 штук этих классов
        val id: String, // 838 непонятное число неизвестно зачем wordId Слово-это группа значений. Мы объединяем значения словом сущность.
        val text: String, // "chair"
        val meanings: List<@RawValue Meaning2>  // и таких 6 штук стульев Кисы Воробьянинова
) : Parcelable

data class Meaning2(
        val id: String, // 1938 - вообще-то это не String а Long
        val partOfSpeechCode: String,  // "n"
        val translation: @RawValue Translation,  // "стул" null
        val previewUrl: String, //"//d2zkmv5t5kao9.cloudfront.net/images/b905a618b56c721ce683164259ac02c4.jpeg?w=96&h=72",
        val imageUrl: String, //"//d2zkmv5t5kao9.cloudfront.net/images/b905a618b56c721ce683164259ac02c4.jpeg?w=640&h=480",
        val transcription: String, //"ʧeə",
        val soundUrl: String //"//d2fmfepycn0xw0.cloudfront.net?gender=male&accent=british&text=chair"
)

@Parcelize
data class Meaning(
    val id: String, // "1938", Meaning id.
    val wordId: Long, // 838, - это id из Word Слово-это группа значений. Мы объединяем значения словом сущность.
    val difficultyLevel: Int?, //1, There are 6 difficultyLevels: 1, 2, 3, 4, 5, 6.
    val partOfSpeechCode: String, // "n", enum class PartOfSpeechCode
    val prefix: String?, // "a", Infinitive particle (to) or articles (a, the).
    val text: String, // "chair",  Meaning text.
    val soundUrl: String, // "//d2fmfepycn0xw0.cloudfront.net?gender=male&accent=british&text=chair", URL to meaning sound.
    val transcription: String, //"ʧeə", IPA phonetic transcription.
    val properties: @RawValue  Properties,
    val updatedAt: String, //"2021-02-10 04:49:35",
    val mnemonics: String?, //null, Поговорка как выучить-запомнить слово-перевод
    val translation: @RawValue Translation,
    val images: List<@RawValue ImageUrl>,  // collection of an images.
    val definition: @RawValue Definition,
    val examples: List<@RawValue Example>, // Usage examples.
    val meaningsWithSimilarTranslation: List<@RawValue MeaningsWithSimilarTranslation>, // Collection of meanings with similar translations.
    val alternativeTranslations: List<@RawValue AlternativeTranslations> // Collection of alternative translations.
) : Parcelable

data class Translation(
        val text: String,  // "стул" A text of a translation.
        val note: String?  // null   A note about translation.
)

data class Properties(
    val collocation: Boolean = false, // false,
   val  countability: String = "", //"c",
    val irregularPlural: Boolean = false, //false,
    val falseFriends: List<String> = arrayListOf() //[]
)

data class Definition(
    val text: String, // "A separate seat for one person, with four legs and a support for the back.",
    val soundUrl: String // "//d2fmfepycn0xw0.cloudfront.net?gender=male&accent=british&text=a+separate+seat+for+one+person+with+four+legs+and+a+support+for+the+back"
)

data class Example(
    val text: String, // "Put an additional [chair], please.",
    val soundUrl: String // //d2fmfepycn0xw0.cloudfront.net?gender=male&accent=british&text=Put+an+additional+chair+please."
)

data class MeaningsWithSimilarTranslation(
    val meaningId: Long, // 1938,
    val frequencyPercent: String, // "50.0",  Percentage of frequency among all other usages. Процент частоты среди всех других видов использования.
    val partOfSpeechAbbreviation: String, // "сущ.",
    val translation: @RawValue Translation
)

data class AlternativeTranslations(
    val text: String, //"company",
    val translation: @RawValue Translation
)

data class ImageUrl(
    val url: String // "//d2zkmv5t5kao9.cloudfront.net/images/b905a618b56c721ce683164259ac02c4.jpeg?w=200&h=150&q=50"
)

data class Url (
    val url: String  // // "//d2fmfepycn0xw0.cloudfront.net?gender=male&accent=british&text=a+separate+seat+for+one+person+with+four+legs+and+a+support+for+the+back"
        )

// Вспомогательный класс для RecyclerView на первом экране - поиск-перевод
data class WordRecycler(
    val idEng: String, // word.id 838 непонятное число неизвестно зачем wordId Слово-это группа значений. Мы объединяем значения словом сущность.
    val textEng: String, // word.text "chair"

    //val meanings: List<@RawValue Meaning2>  // и таких 6 штук стульев Кисы Воробьянинова
    val idRus: String, // 1938 - вообще-то это не String а Long
    val partOfSpeechCode: String,  // "n" код части речи

    //val translation: @RawValue Translation,  // "стул" null
    val textRus: String,  // meaning2.translation.text "стул" A text of a translation.
    val note: String?,  // null   A note about translation.

    val previewUrl: String, //"//d2zkmv5t5kao9.cloudfront.net/images/b905a618b56c721ce683164259ac02c4.jpeg?w=96&h=72",
    val imageUrl: String, //"//d2zkmv5t5kao9.cloudfront.net/images/b905a618b56c721ce683164259ac02c4.jpeg?w=640&h=480",
    val transcription: String, //"ʧeə",
    val soundUrl: String //"//d2fmfepycn0xw0.cloudfront.net?gender=male&accent=british&text=chair"
)  {
    // isNote логическое значение и установите его значение в зависимости от того есть ли Note
    val isNote  // true - есть false - нет
        get() = (note == "") or (note == null)
}
