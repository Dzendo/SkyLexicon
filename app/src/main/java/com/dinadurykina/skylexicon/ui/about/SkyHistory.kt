package com.dinadurykina.skylexicon.ui.about

import androidx.collection.arraySetOf
import com.dinadurykina.skylexicon.network.Meaning
import com.dinadurykina.skylexicon.network.Word

/**
 * Класс хранящий историю поиска и расшифровки слов
 * private val historyWord: MutableList<Word> = arrayListOf() - лист поиск слов,
 * private val historyMeaning: MutableList<Meaning> = arrayListOf(), - лист расшифровки слов
 * Примечание: если они не указаны, т.е. New SkyHistory(),
 * то при создании экземпляра класса создаются новые пустые листы
 */

class SkyHistory (
    private val historyWord: MutableSet<Word> = arraySetOf(),
    private val historyMeaning: MutableSet<Meaning> = arraySetOf(),
    private val historySeans:MutableList<HistorySeans> = arrayListOf()
    ){
    /**
     * Добавление слова в историю поиска
     * @param word класс слова, который надо добавить
     * @return Должно возвращать true - успешно добавила
     * false - отказалась добавлять (пока не сделано)
     */
    fun addHistoryWord(word:Word): Boolean = historyWord.add(word).also { addHistorySeans(1) }


    /**
     * Добавление расшифровки слова в историю поиска
     * @param meaning класс Id номера слова, который надо добавить
     * @return Должно возвращать true - успешно добавила
     * false - отказалась добавлять (пока не сделано)
     */
    fun addHistoryMeaning(meaning: Meaning): Boolean =historyMeaning.add(meaning).also { addHistorySeans(2) }

    /**
     * Функция очистки истории поиска
     */
    fun clearHistory(): Boolean{
        if (historyWord.isEmpty() and historyMeaning.isEmpty()) return false
        historyWord.clear()
        historyMeaning.clear()
        clearHistorySeans()
        return true
    }

    /**
     * Функция запроса истории поиска слов
     * Отвечает  - целое число, сколько запомнено слов в истории
     */
    fun sizeHistoryWord() :Int = historyWord.size

    /**
     * Функция запроса истории расшифровки слов
     * Отвечает  - целое число, сколько запомнено слов в истории
     */
    fun sizeHistoryMeaning() : Int = historyMeaning.size

    fun clearHistorySeans(timerNomber:Int=-1):Boolean{
        historySeans.clear()
        return true
    }
    fun addHistorySeans(timerNomber:Int=-1): Boolean {
        val time = System.currentTimeMillis()
        if (historySeans.isNotEmpty()) with(historySeans){
            this[lastIndex].dataStop = time
            this[lastIndex].duration =
                this[lastIndex].dataStop - this[lastIndex].dataStart
            this[lastIndex].timerNomber = timerNomber
        }
        historySeans.add(HistorySeans(time))
        return true
    }

    fun sizeSeans(timerNomber:Int = -1) :Int  = historySeans.count{ it.timerNomber == timerNomber }
                                              //  = historySeans.filter { it.timerNomber == timerNomber }.size
    fun durationSeans(timerNomber:Int = -1): Long = historySeans.filter { it.timerNomber == timerNomber  }.sumOf { it.duration }
}
data class HistorySeans(val dataStart:Long=-2, var dataStop:Long=-2, var duration:Long=-2,
                 var timerNomber: Int=-2, val what:String="null")
enum class SKYTIMER{
    TIMER_FAG,
    TIMER_WORD,
    TIMER_MEANING
}

/*
sealed class DataItem {
    data class ExampleItem(val example: Example=Example()
    ) : DataItem()
    data class MeaningWithSimilarTranslationItem(
        val meaningWithSimilarTranslation: MeaningsWithSimilarTranslation= MeaningsWithSimilarTranslation()
    ) : DataItem()
    data class AlternativeTranslationsItem(
        val alternativeTranslations: AlternativeTranslations= AlternativeTranslations()
    ) : DataItem()
    enum class VIEW_TYPE {
        ITEM_VIEW_TYPE_EXAMPLE,
        ITEM_VIEW_TYPE_SIMILAR,
        ITEM_VIEW_TYPE_ALTERNATIVE
    }
}
 */