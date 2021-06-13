package com.dinadurykina.skylexicon.ui.about

import com.dinadurykina.skylexicon.network.Meaning
import com.dinadurykina.skylexicon.network.Word


class SkyHistory {
    val historyWord: MutableList<Word> = arrayListOf()
    val historyMeaning: MutableList<Meaning> = arrayListOf()

    fun addHistoryWord(word:Word): Boolean {
        historyWord.add(word)
    return true
    }
    fun addHistoryMeaning(meaning: Meaning): Boolean {
        historyMeaning.add(meaning)
        return true
    }
    fun sizeHistoryWord() :Int {
        return historyWord.size
    }
    fun sizeHistoryMeaning() : Int = historyMeaning.size
}