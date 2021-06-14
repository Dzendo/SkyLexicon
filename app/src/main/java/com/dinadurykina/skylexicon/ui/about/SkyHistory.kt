package com.dinadurykina.skylexicon.ui.about

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
    private val historyWord: MutableList<Word> = arrayListOf(),
    private val historyMeaning: MutableList<Meaning> = arrayListOf(),
    ){
    /**
     * Добавление слова в историю поиска
     * @param word класс слова, который надо добавить
     * @return Должно возвращать true - успешно добавила
     * false - отказалась добавлять (пока не сделано)
     */
    fun addHistoryWord(word:Word): Boolean {
        historyWord.add(word)
    return true
    }

    /**
     * Добавление расшифровки слова в историю поиска
     * @param meaning класс Id номера слова, который надо добавить
     * @return Должно возвращать true - успешно добавила
     * false - отказалась добавлять (пока не сделано)
     */
    fun addHistoryMeaning(meaning: Meaning): Boolean {
        historyMeaning.add(meaning)
        return true
    }

    /**
     * Функция очистки истории поиска
     */
    fun clearHistory(): Boolean{
        historyWord.clear()
        historyMeaning.clear()
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
}