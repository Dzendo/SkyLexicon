package com.dinadurykina.skylexicon.ui.about

import androidx.collection.arraySetOf
import com.dinadurykina.skylexicon.network.Meaning
import com.dinadurykina.skylexicon.network.Word
import org.junit.Assert.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Ignore
import org.junit.Test

/**
 * Create Call Check (CCC) == Создание Вызовите Проверьте == 创建呼叫检查
 * Arrange, Act, Assert (AAA)  == «Упорядочить, Действовать, Утвердить» (AAA) == 组织，行动，批准
 * Given, When, Then (GWT) ==  Дано, Когда, Затем == 定, 当, 然后
 */

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

/**
 * https://www.youtube.com/watch?v=pK7W5npkhho&t=222s
 * testImplementation "com.google.truth:truth:1.1.3"
 * Junit4 --> Junit5
 */

/**
 * Пример простейшего Unit test с ручным внедрением зависимостей
 * без дополнительных лишних сложностей
 * Тестируем класс сохранения истории поиска и расшифровки слов и его функции
 */
class SkyHistoryTest1 {

    /**
     * Проверка, что функция добавления слов в историю работает на пустой истории,
     * если ничего не добавили
     */
    @Test
    fun addHistoryWord_noWord_null() {
        val testHistory = SkyHistory()
        assertEquals(testHistory.sizeHistoryWord(), 0)
    }

    /**
     * Проверка, что функция добавления слов в историю работает при добавлении одного слова
     */
    @Test
    fun addHistoryWord_OneWord_One() {
        val testHistory = SkyHistory()
        val result = testHistory.addHistoryWord(Word(text="Chair"))
        assertEquals(result, true)
        val resultSize = testHistory.sizeHistoryWord()
        assertEquals(resultSize, 1)
    }
    /**
     * Проверка, что функция добавления слов в историю работает при добавлении много слов до 50-ти
     */
    @Test
    fun addHistoryWord_50Word_50() {
        val testHistory = SkyHistory()
        for(i in 1..50){
            testHistory.addHistoryWord(Word(text="Chair $i"))
        }
        assertEquals(testHistory.sizeHistoryWord(), 50)
        assertThat(testHistory.sizeHistoryWord(), `is`(50))
    }

    /**
     * Проверка, что функция добавления расшифровки слов в историю работает на пустой истории,
     * если ничего не расшифровывали
     */
    @Test
    fun addHistoryMeaning_noMeaning_null() {
        val testHistory = SkyHistory()
        assertEquals(testHistory.sizeHistoryMeaning(), 0)
    }

    /**
     * Проверка, что функция добавления слов в историю работает при добавлении одного
     * расшифрованногое слова
     */
    @Test
    fun addHistoryMeaning_OneMeaning_One() {
        val testHistory = SkyHistory()
        val result = testHistory.addHistoryMeaning(Meaning(text="Chair"))
        assertEquals(result, true)
        val resultSize = testHistory.sizeHistoryMeaning()
        assertEquals(resultSize, 1)
    }

    /**
     * Проверка, что функция добавления слов в историю работает при добавлении много расшифрованных
     * слов до 50-ти
     */
    @Test
    fun addHistoryMeaning_50Meaning_50() {
        val testHistory = SkyHistory()
        for(i in 1..50){
            testHistory.addHistoryMeaning(Meaning(text="Chair $i"))
        }
        assertEquals(testHistory.sizeHistoryMeaning(), 50)

        /**
         * То же самое утверждение с библиотекой hamcrest
         */
        assertThat(testHistory.sizeHistoryMeaning(), `is`(50))
    }

    /**
     * Проверка, что функция очистки работает для пустой истории и не ломается
     */
    @Test
    fun clearHistory_noEmpty_0() {
        val testHistory = SkyHistory()
        val result = testHistory.clearHistory()
        assertEquals(result, false)

        /**
         * Ликбез для Junior
         * testHistory.sizeHistoryWord()
         * Запрашиваю у класса размер истории поиска Word
         * val sizeWord
         * Объявляю переменную с именем sizeWord
         * = т.е. то, что справа от равно присваиваю тому что слева от равно
         * итого sizeWord  хранит 0
         */
        val sizeWord=testHistory.sizeHistoryWord()
        assertEquals(sizeWord, 0)
        val sizeMeaning=testHistory.sizeHistoryMeaning()
        assertEquals(sizeMeaning, 0)
    }
    /**
     * Проверка, что функция очистки работает для истории из пяти записей слов, которые искали +
     * из пяти записей слов которые расшифрованы.
     * Поиск/расшифровка одного и того же слова несколько раз считается за один раз
     */
    @Test
    fun clearHistory_5_0() {
        val historyWord: MutableSet<Word> = arraySetOf(
            Word(text="Chair"),
            Word(text="Chairr"),
            Word(text="Chairrr"),
            Word(text="Chairrrr"),
            Word(text="Chairrrrr"),
        )

        val historyMeaning: MutableSet<Meaning> = arraySetOf(
            Meaning(id="1938"),
            Meaning(id="1938r"),
            Meaning(id="1938rr"),
            Meaning(id="1938rrr"),
            Meaning(id="1938rrrr"),
        )

        val testHistory = SkyHistory(historyWord, historyMeaning)
        val sizeWord5=testHistory.sizeHistoryWord()
        assertEquals(sizeWord5, 5)
        val sizeMeaning5=testHistory.sizeHistoryMeaning()
        assertEquals(sizeMeaning5, 5)
        val result = testHistory.clearHistory()
        assertEquals(result, true)
        val sizeWord=testHistory.sizeHistoryWord()
        assertEquals(sizeWord, 0)
        val sizeMeaning=testHistory.sizeHistoryMeaning()
        assertEquals(sizeMeaning, 0)
    }

    /**
     * Проверка, что функция очистки работает для истории до 500 записей слов, которые искали +
     * до 300 записей слов которые расшифрованы.
     * Поиск/расшифровка одного и того же слова несколько раз считается за один раз
     */
    @Test
    fun clearHistory_500_0() {
        val historyWord: MutableSet<Word> = arraySetOf()
        for(i in 1..500){
            historyWord.add(Word(text="Chair $i"))
        }

        val historyMeaning: MutableSet<Meaning> = arraySetOf()
        for(i in 1..300){
            historyMeaning.add(Meaning(text="1938 $i"))
        }

        val testHistory = SkyHistory(historyWord, historyMeaning)
        val sizeWord500=testHistory.sizeHistoryWord()
        assertEquals(sizeWord500, 500)
        val sizeMeaning300=testHistory.sizeHistoryMeaning()
        assertEquals(sizeMeaning300, 300)
        val result = testHistory.clearHistory()
        assertEquals(result, true)
        val sizeWord=testHistory.sizeHistoryWord()
        assertEquals(sizeWord, 0)
        val sizeMeaning=testHistory.sizeHistoryMeaning()
        assertEquals(sizeMeaning, 0)
    }

    /**
     * Тест: пустая история размером 0
     */
    @Test
    fun sizeHistoryWord_0words_equal0() {
        /**
         * Создаем список истрии поиска слов пустой
         */
        val historyWord: MutableSet<Word> = arraySetOf()

        /**
         * SkyHistory(historyWord) -
         * Создаем экземпляр класса История и передаем ему
         * пустой список Истории
         * testHistory - ссылка на ново созданный объект
         */
        val testHistory = SkyHistory(historyWord)

        /**
         * Вызываем из новосозданного экземпляра функцию, которую тестируем
         * В ответ дает размер истории (Int), который загоняем в result
         */
        val result = testHistory.sizeHistoryWord()

        /**
         * Check the result
         * Штатный вопрос JUnit на равентсво
         * assert - утверждаю Equals - что равно
         */
        assertEquals(result, 0)
    }

    /**
     * Тест: что размер истории из 5 записей равен 5
     */
    @Test
    fun sizeHistoryWord_5words_equal5() {

        /**
         * Create an active task
         * Создание активной задачи
         * Создаем список истории поиска слов из 5 записей
         * * остальные поля записи генерируются по умолчанию
         */
        val historyWord: MutableSet<Word> = arraySetOf(
            Word(text="Chair"),
            Word(text="Chairr"),
            Word(text="Chairrr"),
            Word(text="Chairrrr"),
            Word(text="Chairrrrr"),
            )

        /**
         * SkyHistory(historyWord)
         * Создаем экземпляр класса История и передаем ему
         * не пустой список Истории
         * testHistory - ссылка на ново созданный объект
         */
        val testHistory = SkyHistory(historyWord)

        /**
         * Call your function
         * Вызовите свою функцию
         * Вызываем из новосозданного экземпляра функцию, которую тестируем
         * В ответ дает размер истории (Int), который загоняем в result
         */
        val result = testHistory.sizeHistoryWord()

        /**
         * Check the result
         * Проверьте результат
         * Штатный вопрос JUnit на равентсво
         * assert - утверждаю Equals - что равно 5
         */
        assertEquals(result, 5)

        /**
         * Check the result
         * Проверьте результат
         * Не Штатный вопрос hamcrest на равентсво
         * assert - утверждаю Equals - что равно 5
         */
        assertThat(result, `is`(5))
    }

    /**
     * Тест: что размер истории из записей от 1 до 500
     */
    @Test
    fun sizeHistoryWord_ManyWords_equalNO() {

        /**
         * Создаем пустую историю
         */
        val historyWord: MutableSet<Word> = arraySetOf()

        /**
         * В пустой список нагоняем записи.
         * Создаем тестовый случай
         * * остальные поля записи генерируются по умолчанию
         */
          for(i in 1..500){
            historyWord.add(Word(text="Chair $i"))
        }

        /**
         * Создаем экземпляр класса История и передаем ему
         * не пустой список Истории
         * testHistory - ссылка на ново созданный объект
         */
        val testHistory = SkyHistory(historyWord)

        /**
         * Call your function
         * Вызовите свою функцию
         * Вызываем из новосозданного экземпляра функцию, которую тестируем
         * В ответ дает размер истории (Int), который загоняем в result
         */
        val result = testHistory.sizeHistoryWord()

        /**
         * Check the result
         * Проверьте результат
         * Штатный вопрос JUnit на равентсво
         * assert - утверждаю Equals - что равно 500
         */
        assertEquals(result, 500)
    }

    /**
     * Тест: что размер истории из 2 147 483 647 записей создастся не может и будет превышение
     * размера кучи
     */
    @Test
    @Ignore
    fun sizeHistoryWord_LotWords_equalNoSpace() {

        /**
         * Create an active task
         * Создание активной задачи
         * Создаем пустую историю
         */
        val historyWord: MutableSet<Word> = arraySetOf()

        /**
         * В пустой список нагоняем записи.
         * Создаем тестовый случай
         * * остальные поля записи генерируются по умолчанию
         * На каком-то i память и закончится и будет ошибка
         */
        for(i in 1..Int.MAX_VALUE){
            historyWord.add(Word(text="Chair $i"))
        }

        /**
         * Создаем экземпляр класса История и передаем ему
         * не пустой список Истории
         * testHistory - ссылка на ново созданный объект
         * На самом деле, сюда программа не доходит, уже ломается
         */
        val testHistory = SkyHistory(historyWord)

        /**
         * Call your function
         * Вызовите свою функцию
         * Вызываем из новосозданного экземпляра функцию, которую тестируем
         * В ответ дает размер истории (Int), который загоняем в result
         * На самом деле, сюда программа не доходит, уже ломается
         */

        val result = testHistory.sizeHistoryWord()

        /**
         * Check the result
         * Проверьте результат
         * Штатный вопрос JUnit на равентсво
         * assert - утверждаю Equals - что
         * TODO Сделать правильный вопрос на ошибку
         */
        assertEquals(result, Int.MAX_VALUE)
    }

    /**
     * Тест: пустая история размером 0 для расшифрованных слов
     */
    @Test
    fun sizeHistoryMeaning_0Meaning_equal0() {

         /**
         * Создаем список истрии расшифровки слов пустой
         */
        val historyMeaning: MutableSet<Meaning> = arraySetOf()

        /**
         * SkyHistory(historyWord) -
         * Создаем экземпляр класса История и передаем ему
         * пустой список Истории
         * testHistory - ссылка на ново созданный объект
         */
        val testHistory = SkyHistory(historyMeaning=historyMeaning)

        /**
         * Вызываем из новосозданного экземпляра функцию, которую тестируем
         * В ответ дает размер истории (Int), который загоняем в result
         */
        val result = testHistory.sizeHistoryMeaning()

        /**
        * Check the result
        * Штатный вопрос JUnit на равентсво
        * assert - утверждаю Equals - что равно
        */
        assertEquals(result, 0)
    }

    /**
     * Тест: что размер истории из 5 записей равен 5
     */
    @Test
    fun sizeHistoryMeaning_5Meaning_equal5() {

        /**
         * Create an active task
         * Создание активной задачи
         * Создаем список истории поиска слов из 5 записей
         * * остальные поля записи генерируются по умолчанию
         */
        val historyMeaning: MutableSet<Meaning> = arraySetOf(
            Meaning(id="1938"),
            Meaning(id="1938r"),
            Meaning(id="1938rr"),
            Meaning(id="1938rrr"),
            Meaning(id="1938rrrr"),
        )

        /**
         * SkyHistory(historyWord)
         * Создаем экземпляр класса История и передаем ему
         * не пустой список Истории
         * testHistory - ссылка на ново созданный объект
         */
        val testHistory = SkyHistory(historyMeaning=historyMeaning)

        /**
         * Call your function
         * Вызовите свою функцию
         * Вызываем из новосозданного экземпляра функцию, которую тестируем
         * В ответ дает размер истории (Int), который загоняем в result
         */
        val result = testHistory.sizeHistoryMeaning()

        /**
         * Check the result
         * Проверьте результат
         * Штатный вопрос JUnit на равентсво
         * assert - утверждаю Equals - что равно 5
         */
        assertEquals(result, 5)

        /**
         * Check the result
         * Проверьте результат
         * Не Штатный вопрос hamcrest на равентсво
         * assert - утверждаю Equals - что равно 5
         */
        assertThat(result, `is`(5))
    }

    /**
     * Тест: что размер истории из записей от 1 до 500
     */
    @Test
    fun sizeHistoryMeaning_ManyMeanings_equalNO() {

        /**
         * Создаем пустую историю
         */
        val historyMeaning: MutableSet<Meaning> = arraySetOf()

        /**
         * В пустой список нагоняем записи.
         * Создаем тестовый случай
         * * остальные поля записи генерируются по умолчанию
         */
        for(i in 1..500){
            historyMeaning.add(Meaning(text="1938 $i"))
        }

        /**
         * Создаем экземпляр класса История и передаем ему
         * не пустой список Истории
         * testHistory - ссылка на ново созданный объект
         */
        val testHistory = SkyHistory(historyMeaning=historyMeaning)

        /**
         * Call your function
         * Вызовите свою функцию
         * Вызываем из новосозданного экземпляра функцию, которую тестируем
         * В ответ дает размер истории (Int), который загоняем в result
         */
        val result = testHistory.sizeHistoryMeaning()

        /**
         * Check the result
         * Проверьте результат
         * Штатный вопрос JUnit на равентсво
         * assert - утверждаю Equals - что равно 500
         */
        assertEquals(result, 500)

        /**
         * Check the result
         * Проверьте результат
         * Не Штатный вопрос hamcrest на равентсво
         * assert - утверждаю Equals - что равно 5
         */
        assertThat(result, `is`(500))
    }

    /**
     * Тест: что размер истории из 2 147 483 647 записей создастся не может и будет превышение
     * размера кучи
     */
    @Test
    @Ignore
    fun sizeHistoryMeaning_LotMeanings_equalNoSpace() {
        /**
         * Create an active task
         * Создание активной задачи
         * Создаем пустую историю
         */
        val historyMeaning: MutableSet<Meaning> = arraySetOf()

        /**
         * В пустой список нагоняем записи.
         * Создаем тестовый случай
         * * остальные поля записи генерируются по умолчанию
         * На каком-то i память и закончится и будет ошибка
         */
        for(i in 1..Int.MAX_VALUE){
            historyMeaning.add(Meaning(text="1938 $i"))
        }

        /**
         * Создаем экземпляр класса История и передаем ему
         * не пустой список Истории
         * testHistory - ссылка на ново созданный объект
         * На самом деле, сюда программа не доходит, уже ломается
         */
        val testHistory = SkyHistory(historyMeaning=historyMeaning)
        /**
         * Call your function
         * Вызовите свою функцию
         * Вызываем из новосозданного экземпляра функцию, которую тестируем
         * В ответ дает размер истории (Int), который загоняем в result
         * На самом деле, сюда программа не доходит, уже ломается
         */
        val result = testHistory.sizeHistoryMeaning()
        /**
         * Check the result
         * Проверьте результат
         * Штатный вопрос JUnit на равентсво
         * assert - утверждаю Equals - что
         * TODO Сделать правильный вопрос на ошибку
         */
        assertEquals(result, Int.MAX_VALUE)
    }
}