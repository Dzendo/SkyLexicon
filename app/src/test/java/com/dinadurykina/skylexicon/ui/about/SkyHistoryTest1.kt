package com.dinadurykina.skylexicon.ui.about

import com.dinadurykina.skylexicon.network.Meaning
import com.dinadurykina.skylexicon.network.Word
import org.junit.Assert.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

// Create Call Check (CCC) == Создание Вызовите Проверьте
// Arrange, Act, Assert (AAA)  == «Упорядочить, Действовать, Утвердить» (AAA)
// Given, When, Then (GWT) ==  Дано, Когда, Затем

/**
 * Имена тестов
 * Названия тестов должны быть описательными. Соглашение об именах в этой кодовой лаборатории:
 * subjectUnderTest_actionOrInput_resultState
 * getActiveAndCompletedStats_noCompleted_returnsHundredZero
 * Тестируемый объект - это тестируемый метод или класс ( getActiveAndCompletedStats).
 * Далее идет действие или input ( noCompleted).
 * Наконец-то у вас есть ожидаемый результат ( returnsHundredZero).
 *
 */
// https://www.youtube.com/watch?v=pK7W5npkhho&t=222s
// testImplementation "com.google.truth:truth:1.1.3"
// Junit4 --> Junit5
class SkyHistoryTest1 {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun addHistoryWord() {
        val testHistory = SkyHistory()
        assertEquals(testHistory.sizeHistoryWord(), 0)
        val result = testHistory.addHistoryWord(Word(text="Chairr"))
        assertEquals(result, true)
        val resultSize = testHistory.sizeHistoryWord()
        assertEquals(resultSize, 1)
        testHistory.clearHistory()
        for(i in 1..50){
            testHistory.addHistoryWord(Word(text="Chair $i"))
        }
        assertEquals(testHistory.sizeHistoryWord(), 50)
        assertThat(testHistory.sizeHistoryWord(), `is`(50))

    }

    //@Test
    fun addHistoryMeaning() {
    }

    //@Test
    fun clearHistory() {
    }

    @Test
    fun sizeHistoryWord_0words_equal0() {
        // Create an active task
        // Создание активной задачи
        val historyWord: MutableList<Word> = arrayListOf()
        val testHistory = SkyHistory(historyWord)
        // Call your function
        // Вызовите свою функцию
        val result = testHistory.sizeHistoryWord()
        // Check the result
        // Проверьте результат
        assertEquals(result, 0)
    }
    @Test
    fun sizeHistoryWord_5words_equal5() {
        // Create an active task
        // Создание активной задачи
        val historyWord: MutableList<Word> = arrayListOf(
            Word(text="Chair"),
            Word(text="Chairr"),
            Word(text="Chairrr"),
            Word(text="Chairrrr"),
            Word(text="Chairrrrr"),
            )
        val testHistory = SkyHistory(historyWord)
        // Call your function
        // Вызовите свою функцию
        val result = testHistory.sizeHistoryWord()
        // Check the result
        // Проверьте результат
        assertEquals(result, 5)
    }

    @Test
    fun sizeHistoryWord_ManyWords_equalNO() {
        // Create an active task
        // Создание активной задачи
        val historyWord: MutableList<Word> = arrayListOf()
        for(i in 1..50){
            historyWord.add(Word(text="Chair $i"))
        }

        val testHistory = SkyHistory(historyWord)
        // Call your function
        // Вызовите свою функцию
        val result = testHistory.sizeHistoryWord()
        // Check the result
        // Проверьте результат
        assertEquals(result, 50)
    }
// 2 147 483 647
    @Test
    @Ignore
    fun sizeHistoryWord_LotWords_equalNoSpace() {
        // Create an active task
        // Создание активной задачи
        val historyWord: MutableList<Word> = arrayListOf()
        for(i in 1..Int.MAX_VALUE){
            historyWord.add(Word(text="Chair $i"))
        }

        val testHistory = SkyHistory(historyWord)
        // Call your function
        // Вызовите свою функцию
        val result = testHistory.sizeHistoryWord()
        // Check the result
        // Проверьте результат
        assertEquals(result, Int.MAX_VALUE)
    }

    @Test
    fun sizeHistoryMeaning_0Meaning_equal0() {
        // Create an active task
        // Создание активной задачи
        val historyMeaning: MutableList<Meaning> = arrayListOf()
        val testHistory = SkyHistory(historyMeaning=historyMeaning)
        // Call your function
        // Вызовите свою функцию
        val result = testHistory.sizeHistoryMeaning()
        // Check the result
        // Проверьте результат
        assertEquals(result, 0)
    }
    @Test
    fun sizeHistoryMeaning_5Meaning_equal5() {
        // Create an active task
        // Создание активной задачи
        val historyMeaning: MutableList<Meaning> = arrayListOf(
            Meaning(id="1938"),
            Meaning(id="1938r"),
            Meaning(id="1938rr"),
            Meaning(id="1938rrr"),
            Meaning(id="1938rrrr"),
        )
        val testHistory = SkyHistory(historyMeaning=historyMeaning)
        // Call your function
        // Вызовите свою функцию
        val result = testHistory.sizeHistoryMeaning()
        // Check the result
        // Проверьте результат
        assertEquals(result, 5)
    }

    @Test
    fun sizeHistoryMeaning_ManyMeanings_equalNO() {
        // Create an active task
        // Создание активной задачи
        val historyMeaning: MutableList<Meaning> = arrayListOf()
        for(i in 1..50){
            historyMeaning.add(Meaning(text="1938 $i"))
        }

        val testHistory = SkyHistory(historyMeaning=historyMeaning)
        // Call your function
        // Вызовите свою функцию
        val result = testHistory.sizeHistoryMeaning()
        // Check the result
        // Проверьте результат
        assertEquals(result, 50)
    }
    // 2 147 483 647
    @Test
    @Ignore
    fun sizeHistoryMeaning_LotMeanings_equalNoSpace() {
        // Create an active task
        // Создание активной задачи
        val historyMeaning: MutableList<Meaning> = arrayListOf()
        for(i in 1..Int.MAX_VALUE){
            historyMeaning.add(Meaning(text="1938 $i"))
        }

        val testHistory = SkyHistory(historyMeaning=historyMeaning)
        // Call your function
        // Вызовите свою функцию
        val result = testHistory.sizeHistoryMeaning()
        // Check the result
        // Проверьте результат
        assertEquals(result, Int.MAX_VALUE)
    }
}