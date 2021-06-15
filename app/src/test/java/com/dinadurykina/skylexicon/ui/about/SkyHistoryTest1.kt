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

/** Русский
 * Имена тестов
 * Названия тестов должны быть описательными. Соглашение об именах в этой кодовой лаборатории:
 * subjectUnderTest_actionOrInput_resultState
 * getActiveAndCompletedStats_noCompleted_returnsHundredZero
 * Тестируемый объект - это тестируемый метод или класс ( getActiveAndCompletedStats).
 * Далее идет действие или input ( noCompleted).
 * Наконец-то у вас есть ожидаемый результат ( returnsHundredZero).
 *
 */

/** Eng
 * Test names
 * Test names should be descriptive. Naming convention in this code lab:
 * subjectUnderTest_actionOrInput_resultState
 * getActiveAndCompletedStats_noCompleted_returnsHundredZero
 * A test object is a test method or class ( getActiveAndCompletedStats).
 * Next comes the action or input ( noCompleted).
 * Finally, you have the expected result ( returnsHundredZero). *
 */

/** 中文
 * 测试名称
 * 测试名称应具有描述性。 此代码实验室中的命名约定:
 * subjectUnderTest_actionOrInput_resultState
 * getActiveAndCompletedStats_noCompleted_returnsHundredZero
 * 测试对象是测试方法或类 ( getActiveAndCompletedStats).
 * 接下来是行动或 input ( noCompleted).
 * 最后，你有预期的结果 ( returnsHundredZero).
 *
 */
/**
 * https://www.youtube.com/watch?v=pK7W5npkhho&t=222s
 * testImplementation "com.google.truth:truth:1.1.3"
 * Junit4 --> Junit5
 */

/** Русский
 * Пример простейшего Unit test с ручным внедрением зависимостей
 * без дополнительных лишних сложностей
 * Тестируем класс сохранения истории поиска и расшифровки слов и его функции
 */

/** Eng
 * Example of a simple Unit test with manual dependency injection
 * without additional unnecessary difficulties
 * Testing the class for saving the search history and decoding words and its functions
 */

/** 中文
 * 使用手动依赖注入的简单单元测试示例
 * 没有额外的不必要的困难
 * 测试保存搜索历史和解码单词及其功能的类
 */

class SkyHistoryTest1 {

    /** Русский
     * Проверка, что функция добавления слов в историю работает на пустой истории,
     * если ничего не добавили
     */

    /** Eng
     * Checking that the add words to history function works on an empty history,
     * if you didn't add anything
     */

    /** 中文
     * 检查将单词添加到历史记录功能是否适用于空历史记录,
     * 如果你没有添加任何东西
     */
    @Test
    fun addHistoryWord_noWord_null() {
        val testHistory = SkyHistory()
        assertEquals(testHistory.sizeHistoryWord(), 0)
    }

    /** Русский
     * Проверка, что функция добавления слов в историю работает при добавлении одного слова
     */

    /** Eng
     * Checking that the add words to history function works when you add a single word
     */

    /** 中文
     * 检查添加单词到历史记录功能在添加单个单词时是否有效
     */
    @Test
    fun addHistoryWord_OneWord_One() {
        val testHistory = SkyHistory()
        val result = testHistory.addHistoryWord(Word(text="Chair"))
        assertEquals(result, true)
        val resultSize = testHistory.sizeHistoryWord()
        assertEquals(resultSize, 1)
    }
    /** Русский
     * Проверка, что функция добавления слов в историю работает при добавлении много слов до 50-ти
     */

    /** Eng
     * Check that the add words to history function works when adding a lot of words up to 50
     */

    /** 中文
     * 检查添加单词到历史记录功能在添加大量单词时是否有效高达50
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

    /** Русский
     * Проверка, что функция добавления расшифровки слов в историю работает на пустой истории,
     * если ничего не расшифровывали
     */

    /** Eng
     * Check that the function of adding word transcripts to the history works on an empty history,
     * if nothing was decrypted
     */

    /** 中文
     *检查将单词成绩单添加到历史记录的功能是否适用于空历史记录,
     * 如果没有解密
     */
    @Test
    fun addHistoryMeaning_noMeaning_null() {
        val testHistory = SkyHistory()
        assertEquals(testHistory.sizeHistoryMeaning(), 0)
    }

    /** Русский
     * Проверка, что функция добавления слов в историю работает при добавлении одного
     * расшифрованного слова
     */

    /** Eng
     * Check that the function of adding words to the history works when you add one
     * decrypted word
     */

    /** 中文
     *当您添加一个时，请检查添加单词到历史记录的功能是否有效
     *解密字
     */
    @Test
    fun addHistoryMeaning_OneMeaning_One() {
        val testHistory = SkyHistory()
        val result = testHistory.addHistoryMeaning(Meaning(text="Chair"))
        assertEquals(result, true)
        val resultSize = testHistory.sizeHistoryMeaning()
        assertEquals(resultSize, 1)
    }

    /** Русский
     * Проверка, что функция добавления слов в историю работает при добавлении много расшифрованных
     * слов до 50-ти
     */

    /** Eng
     * Check that the function of adding words to the history works when adding a lot of deciphered words .
     * up to 50 words
     */

    /** 中文
     *在添加大量破译单词时，请检查向历史添加单词的功能是否有效。
     *最多50个字
     */
    @Test
    fun addHistoryMeaning_50Meaning_50() {
        val testHistory = SkyHistory()
        for(i in 1..50){
            testHistory.addHistoryMeaning(Meaning(text="Chair $i"))
        }
        assertEquals(testHistory.sizeHistoryMeaning(), 50)

        /** Русский
         * То же самое утверждение с библиотекой hamcrest
         */

        /** Eng
         * Same statement with the library hamcrest
         */

        /** 中文
         * 与库相同的语句 hamcrest
         */
        assertThat(testHistory.sizeHistoryMeaning(), `is`(50))
    }

    /** Русский
     * Проверка, что функция очистки работает для пустой истории и не ломается
     */

    /** Eng
     * Checking that the cleanup function works for an empty history and doesn't break
     */

    /** 中文
     * 检查cleanup函数是否适用于空历史记录，并且不会中断
     */
    @Test
    fun clearHistory_noEmpty_0() {
        val testHistory = SkyHistory()
        val result = testHistory.clearHistory()
        assertEquals(result, false)

        /** Русский
         * Ликбез для Junior
         * testHistory.sizeHistoryWord()
         * Запрашиваю у класса размер истории поиска Word
         * val sizeWord
         * Объявляю переменную с именем sizeWord
         * = т.е. то, что справа от равно присваиваю тому что слева от равно
         * итого sizeWord  хранит 0
         */

        /** Eng
         * Explanation for Junior
         * testHistory.sizeHistoryWord()
         * Requesting the size of the Word search history from the class
         * val sizeWord
         * Declaring a variable named sizeWord
         * = that is, what is to the right of is equal to assign to what is to the left of is equal to
         * total sizeWord stores 0
         */

        /** 中文
         * 解释 Junior
         * testHistory.sizeHistoryWord()
         * 从类请求搜索历史记录的大小 Word
         * val sizeWord
         * 使用名称声明变量 sizeWord
         * = 也就是说，右边的东西等于分配给左边的东西等于
         * 总计 sizeWord 商店 0
         */
        val sizeWord=testHistory.sizeHistoryWord()
        assertEquals(sizeWord, 0)
        val sizeMeaning=testHistory.sizeHistoryMeaning()
        assertEquals(sizeMeaning, 0)
    }
    /** Русский
     * Check that the cleanup function works for the history of the five entries of the words that were searched +
     * out of five entries of words that are deciphered.
     * Searching/decoding the same word multiple times counts at a time
     */

    /** Eng
     * Проверка, что функция очистки работает для истории из пяти записей слов, которые искали +
     * из пяти записей слов которые расшифрованы.
     * Поиск/расшифровка одного и того же слова несколько раз считается за один раз
     */

    /** 中文
     *检查清理功能是否适用于搜索的单词的五个条目的历史记录+
     *出被破译的话五个条目。
     *一次多次搜索/解码相同的单词计数
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

    /** Русский
     * Проверка, что функция очистки работает для истории до 500 записей слов, которые искали +
     * до 300 записей слов которые расшифрованы.
     * Поиск/расшифровка одного и того же слова несколько раз считается за один раз
     */

    /** Eng
     * Check that the cleanup function works for the history of up to 500 entries of words that were searched +
     * up to 300 entries of words that are decoded.
     * Searching/decoding the same word multiple times counts at a time
     */

    /** 中文
     *检查清理功能适用于搜索最多500个单词条目的历史记录+
     *最多300个被解码的单词条目。
     *一次多次搜索/解码相同的单词计数
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

    /** Русский
     * Тест: пустая история размером 0
     */

    /** Eng
     * Test: empty history size 0
     */

    /** 中文
     * 测试：空历史大小0
     */
    @Test
    fun sizeHistoryWord_0words_equal0() {
        /** Русский
         * Создаем список истрии поиска слов пустой
         */

        /** Eng
         * Creating a list of Istria search words empty
         */

        /** 中文
         * 创建伊斯特拉搜索词列表空
         */
        val historyWord: MutableSet<Word> = arraySetOf()

        /** Русский
         * SkyHistory(historyWord) -
         * Создаем экземпляр класса История и передаем ему
         * пустой список Истории
         * testHistory - ссылка на ново созданный объект
         */

        /** Eng
         * SkyHistory(historyWord) -
         * Creating an instance of the History class and passing it to it
         * empty History list
         * testHistory-reference to the newly created object
         */

        /** 中文
         * SkyHistory(historyWord) -
         * 创建History类的实例并将其传递给它
         *空历史列表
         * testHistory - 链接到新创建的对象
         */
        val testHistory = SkyHistory(historyWord)

        /** Русский
         * Вызываем из новосозданного экземпляра функцию, которую тестируем
         * В ответ дает размер истории (Int), который загоняем в result
         */

        /** Eng
         * We call the function that we are testing from the newly created instance
         * In response, it gives the size of the history (Int), which we drive into the result
         */

        /** 中文
         * 我们从新创建的实例调用我们正在测试的函数
         * 作为响应，它给出了历史记录的大小（Int），我们将其驱动到结果中
         */
        val result = testHistory.sizeHistoryWord()

        /** Русский
         * Штатный вопрос JUnit на равентсво
         * assert - утверждаю Equals - что равно
         */

        /** Eng
         * Check the result
         * JUnit staff question on equality
         * assert-I assert Equals - what is equal to
         */

        /** 中文
         * 检查结果
         * JUnit工作人员关于平等的问题
         * assert - 我同意 Equals - 什么等于
         */
        assertEquals(result, 0)
    }

    /** Русский
     * Тест: что размер истории из 5 записей равен 5
     */

    /** Eng
     * Test: that the size of the history of 5 records is 5
     */

    /** 中文
     * 测试：认为5条记录的历史记录的大小是5
     */
    @Test
    fun sizeHistoryWord_5words_equal5() {

        /** Русский
         * Создание активной задачи
         * Создаем список истории поиска слов из 5 записей
         * * остальные поля записи генерируются по умолчанию
         */

        /** Eng
         * Create an active task
         * Create an active task
         * Creating a list of the search history of words from 5 entries
         * * other fields of the record are generated by default
         */

        /** 中文
         *创建活动任务
         *从5个条目创建单词的搜索历史列表
         **记录的其他字段默认生成
         */
        val historyWord: MutableSet<Word> = arraySetOf(
            Word(text="Chair"),
            Word(text="Chairr"),
            Word(text="Chairrr"),
            Word(text="Chairrrr"),
            Word(text="Chairrrrr"),
            )

        /** Русский
         * SkyHistory(historyWord)
         * Создаем экземпляр класса История и передаем ему
         * не пустой список Истории
         * testHistory - ссылка на ново созданный объект
         */

        /** Eng
         * SkyHistory(historyWord)
         * Creating an instance of the History class and passing it to it
         * not an empty History list
         * testHistory-reference to the newly created object
         */

        /** 中文
         * SkyHistory(historyWord)
         * 创建History类的实例并将其传递给它
         *不是空的历史列表
         * testHistory - 链接到新创建的对象
         */
        val testHistory = SkyHistory(historyWord)

        /** Русский
         * Вызовите свою функцию
         * Вызываем из новосозданного экземпляра функцию, которую тестируем
         * В ответ дает размер истории (Int), который загоняем в result
         */

        /** Eng
         * Call your function
         * Calling the function that we are testing from the newly created instance
         * In response, it gives the size of the history (Int), which we drive into the result
         */

        /** 中文
         *调用函数
         *从新创建的实例调用我们正在测试的函数
         *作为响应，它给出了历史记录的大小（Int），我们将其驱动到结果中
         */
        val result = testHistory.sizeHistoryWord()

        /** Русский
         * Проверьте результат
         * Штатный вопрос JUnit на равенство
         * assert - утверждаю Equals - что равно 5
         */

        /** Eng
         * Check the result
         * JUnit staff question on equality
         * assert-I claim Equals - which is equal to 5
         */

        /** 中文
         * 检查结果
         * 员工问题 JUnit 关于平等
         * assert - 我同意 Equals - 什么等于 5
         */
        assertEquals(result, 5)

        /** Русский
         * Проверьте результат
         * Штатный вопрос hamcrest на равенство
         * assert - утверждаю Equals - что равно 5
         */

        /** Eng
         * Check the result
         * hamcrest staff question on equality
         * assert-I claim Equals - which is equal to 5
         */

        /** 中文
         * 检查结果
         * 员工问题 hamcrest 关于平等
         * assert - 我同意 Equals - 什么等于 5
         */
        assertThat(result, `is`(5))
    }

    /** Русский
     * Тест: что размер истории из записей от 1 до 500
     */

    /** Eng
     * Test: what is the size of the history of records from 1 to 500
     */

    /** 中文
     * 测试：从1到500的记录历史的大小是多少
     */
    @Test
    fun sizeHistoryWord_ManyWords_equalNO() {

        /** Русский
         * Создаем пустую историю
         */

        /** Eng
         * Creating an empty history
         */

        /** 中文
         * 创建空历史记录
         */
        val historyWord: MutableSet<Word> = arraySetOf()

        /** Русский
         * В пустой список нагоняем записи.
         * Создаем тестовый случай
         * * остальные поля записи генерируются по умолчанию
         */

        /** Eng
         * In an empty list, we catch up with the entries.
         * Creating a test case
         * * other fields of the record are generated by default
         */

        /** 中文
         *在一个空列表中，我们赶上了条目。
         *创建测试用例
         **记录的其他字段默认生成
         */
          for(i in 1..500){
            historyWord.add(Word(text="Chair $i"))
        }

        /** Русский
         * Создаем экземпляр класса История и передаем ему
         * не пустой список Истории
         * testHistory - ссылка на ново созданный объект
         */

        /** Eng
         * Creating an instance of the History class and passing it to it
         * not an empty History list
         * testHistory - link to the newly created object
         */

        /** 中文
         *创建History类的实例并将其传递给它
         *不是空的历史列表
         * testHistory - 链接到新创建的对象
         */
        val testHistory = SkyHistory(historyWord)

        /** Русский
         * Вызовите свою функцию
         * Вызываем из новосозданного экземпляра функцию, которую тестируем
         * В ответ дает размер истории (Int), который загоняем в result
         */

        /** Eng
         * Call your function
         * Calling the function that we are testing from the newly created instance
         * In response, it gives the size of the history (Int), which we drive into the result
         */

        /** 中文
         *调用函数
         *从新创建的实例调用我们正在测试的函数
         *作为响应，它给出了历史记录的大小（Int），我们将其驱动到结果中
         */
        val result = testHistory.sizeHistoryWord()

        /** Русский
         * Проверьте результат
         * Штатный вопрос JUnit на равенство
         * assert - утверждаю Equals - что равно 500
         */

        /** Eng
         * Check the result
         * JUnit staff question on equality
         * assert-I claim Equals - which is equal to 500
         */

        /** 中文
         * 检查结果
         * 员工问题 JUnit 关于平等
         * assert - 我同意 Equals - 什么等于 500
         */
        assertEquals(result, 500)
    }

    /** Русский
     * Тест: что размер истории из 2 147 483 647 записей создастся не может и будет превышение
     * размера кучи
     */

    /** Eng
     * Test: that the history size of 2 147 483 647 records will not be created and will exceed
     * heap size
     */

    /** 中文
     * 测试：不会创建 2 147 483 647条记录的历史记录大小，并且将超过
     * 堆大小
     */
    @Test
    @Ignore
    fun sizeHistoryWord_LotWords_equalNoSpace() {

        /** Русский
         * Создаем пустую историю
         */

        /** Eng
         * Creating an empty history
         */

        /** 中文
         *创建一个空的历史记录
         */
        val historyWord: MutableSet<Word> = arraySetOf()

        /** Русский
         * В пустой список нагоняем записи.
         * Создаем тестовый случай
         * * остальные поля записи генерируются по умолчанию
         * На каком-то i память и закончится и будет ошибка
         */

        /** Eng
         * In an empty list, we catch up with the entries.
         * Creating a test case
         * * other fields of the record are generated by default
         * At some point i memory will run out and there will be an error
         */

        /** 中文
         *在一个空列表中，我们赶上了条目。
         *创建测试用例
         **记录的其他字段默认生成
         *在某些时候，我的内存将耗尽，会有一个错误
         */
        for(i in 1..Int.MAX_VALUE){
            historyWord.add(Word(text="Chair $i"))
        }

        /** Русский
         * Создаем экземпляр класса История и передаем ему
         * не пустой список Истории
         * testHistory - ссылка на ново созданный объект
         * На самом деле, сюда программа не доходит, уже ломается
         */

        /** Eng
         * Creating an instance of the History class and passing it to it
         * not an empty History list
         * testHistory - link to the newly created object
         * In fact, the program does not reach here, it already breaks down
         */

        /** 中文
         * 创建History类的实例并将其传递给它
         *不是空的历史列表
         * testHistory - 链接到新创建的对象
         *事实上，程序没有到达这里，它已经分解了
         */
        val testHistory = SkyHistory(historyWord)

        /** Русский
         * Вызовите свою функцию
         * Вызываем из новосозданного экземпляра функцию, которую тестируем
         * В ответ дает размер истории (Int), который загоняем в result
         * На самом деле, сюда программа не доходит, уже ломается
         */

        /** Eng
         * Call your function
         * We call the function that we are testing from the newly created instance
         * In response, it gives the size of the history (Int), which we drive into the result
         * In fact, the program does not reach here, it already breaks down
         */

        /** 中文
         *调用函数
         *从新创建的实例调用我们正在测试的函数
         *作为响应，它给出了历史记录的大小（Int），我们将其驱动到结果中
         *事实上，程序没有到达这里，它已经分解了
         */

        val result = testHistory.sizeHistoryWord()

        /** Русский
         * Проверьте результат
         * Штатный вопрос JUnit на равенство
         * assert - утверждаю Equals - что
         * TODO Сделать правильный вопрос на ошибку
         */

        /** Eng
         * Check the result
         * JUnit staff question on equality
         * assert-I claim Equals - which
         * TODO making the right question on a mistake
         */

        /** 中文
         * 检查结果
         * 员工问题 JUnit 关于平等
         * assert - 我同意 Equals - 什么
         * TODO 对错误做出正确的问题
         */
        assertEquals(result, Int.MAX_VALUE)
    }

    /** Русский
     * Тест: пустая история размером 0 для расшифрованных слов
     */

    /** Eng
     * Test: empty history of size 0 for deciphered words
     */

    /** 中文
     * 测试：大小为0的破译单词的空历史记录
     */
    @Test
    fun sizeHistoryMeaning_0Meaning_equal0() {

         /** Русский
         * Создаем список истории расшифровки слов пустой
         */

        /** Eng
         * Creating a list of History decryption words empty
         */

        /** 中文
         * 创建解码词空的历史列表
         */
        val historyMeaning: MutableSet<Meaning> = arraySetOf()

        /** Русский
         * SkyHistory(historyWord) -
         * Создаем экземпляр класса История и передаем ему
         * пустой список Истории
         * testHistory - ссылка на ново созданный объект
         */

        /** Eng
         * SkyHistory(historyWord) -
         * Creating an instance of the History class and passing it to it
         * empty History list
         * testHistory - link to the newly created object
         */

        /** 中文
         * SkyHistory(historyWord) -
         * 创建History类的实例并将其传递给它
         * 空历史列表
         * testHistory - с链接到新创建的对象
         */
        val testHistory = SkyHistory(historyMeaning=historyMeaning)

        /** Русский
         * Вызовите свою функцию
         * Вызываем из новосозданного экземпляра функцию, которую тестируем
         * В ответ дает размер истории (Int), который загоняем в result
         */

        /** Eng
         * Call your function
         * Calling the function that we are testing from the newly created instance
         * In response, it gives the size of the history (Int), which we drive into the result
         */

        /** 中文
         *调用函数
         *从新创建的实例调用我们正在测试的函数
         *作为响应，它给出了历史记录的大小（Int），我们将其驱动到结果中
         */
        val result = testHistory.sizeHistoryMeaning()

        /** Русский
         * Проверьте результат
         * Штатный вопрос hamcrest на равенство
         * assert - утверждаю Equals - что равно 0
         */

        /** Eng
         * Check the result
         * hamcrest staff question on equality
         * assert-I claim Equals - which is equal to 0
         */

        /** 中文
         * 检查结果
         * 员工问题 hamcrest 关于平等
         * assert - 我同意 Equals - 什么等于 0
         */
        assertEquals(result, 0)
    }

    /** Русский
     * Тест: что размер истории из 5 записей равен 5
     */

    /** Eng
     * Test: that the size of the history of 5 records is 5
     */

    /** 中文
     * 测试：认为5条记录的历史记录的大小是5
     */
    @Test
    fun sizeHistoryMeaning_5Meaning_equal5() {

        /** Русский
         * Создание активной задачи
         * Создаем список истории поиска слов из 5 записей
         * * остальные поля записи генерируются по умолчанию
         */

        /** Eng
         * Create an active task
         * Creating a list of the search history of words from 5 entries
         * * other fields of the record are generated by default
         */

        /** 中文
         * 创建活动任务
         * 从5个条目创建单词的搜索历史列表
         * * 记录的其他字段默认生成
         */
        val historyMeaning: MutableSet<Meaning> = arraySetOf(
            Meaning(id="1938"),
            Meaning(id="1938r"),
            Meaning(id="1938rr"),
            Meaning(id="1938rrr"),
            Meaning(id="1938rrrr"),
        )

        /** Русский
         * SkyHistory(historyWord) -
         * Создаем экземпляр класса История и передаем ему
         * пустой список Истории
         * testHistory - ссылка на ново созданный объект
         */

        /** Eng
         * SkyHistory(historyWord) -
         * Creating an instance of the History class and passing it to it
         * empty History list
         * testHistory - link to the newly created object
         */

        /** 中文
         * SkyHistory(historyWord) -
         * 创建History类的实例并将其传递给它
         * 空历史列表
         * testHistory - с链接到新创建的对象
         */
        val testHistory = SkyHistory(historyMeaning=historyMeaning)

        /** Русский
         * Вызовите свою функцию
         * Вызываем из новосозданного экземпляра функцию, которую тестируем
         * В ответ дает размер истории (Int), который загоняем в result
         */

        /** Eng
         * Call your function
         * Calling the function that we are testing from the newly created instance
         * In response, it gives the size of the history (Int), which we drive into the result
         */

        /** 中文
         *调用函数
         *从新创建的实例调用我们正在测试的函数
         *作为响应，它给出了历史记录的大小（Int），我们将其驱动到结果中
         */
        val result = testHistory.sizeHistoryMeaning()

        /** Русский
         * Проверьте результат
         * Штатный вопрос JUnit на равенство
         * assert - утверждаю Equals - что равно 5
         */

        /** Eng
         * Check the result
         * JUnit staff question on equality
         * assert-I claim Equals - which is equal to 5
         */

        /** 中文
         * 检查结果
         * 员工问题 JUnit 关于平等
         * assert - 我同意 Equals - 什么等于 5
         */
        assertEquals(result, 5)

        /** Русский
         * Проверьте результат
         * Штатный вопрос hamcrest на равенство
         * assert - утверждаю Equals - что равно 5
         */

        /** Eng
         * Check the result
         * hamcrest staff question on equality
         * assert-I claim Equals - which is equal to 5
         */

        /** 中文
         * 检查结果
         * 员工问题 hamcrest 关于平等
         * assert - 我同意 Equals - 什么等于 5
         */
        assertThat(result, `is`(5))
    }

    /** Русский
     * Тест: что размер истории из записей от 1 до 500
     */

    /** Eng
     * Test: that the size of the history of 1 records is 500
     */

    /** 中文
     * 测试：认为1条记录的历史记录的大小是500
     */
    @Test
    fun sizeHistoryMeaning_ManyMeanings_equalNO() {

        /** Русский
         * Создаем пустую историю
         */

        /** Eng
         * Creating an empty history
         */

        /** 中文
         *创建一个空的历史记录
         */
        val historyMeaning: MutableSet<Meaning> = arraySetOf()

        /** Русский
         * В пустой список нагоняем записи.
         * Создаем тестовый случай
         * * остальные поля записи генерируются по умолчанию
         */

        /** Eng
         * In an empty list, we catch up with the entries.
         * Creating a test case
         * * the other fields of the record are generated by default
         */

        /** 中文
         * 在一个空列表中，我们赶上了条目。
         * 创建测试用例
         * *记录的其他字段默认生成
         */
        for(i in 1..500){
            historyMeaning.add(Meaning(text="1938 $i"))
        }

        /** Русский
         * SkyHistory(historyWord) -
         * Создаем экземпляр класса История и передаем ему
         * пустой список Истории
         * testHistory - ссылка на ново созданный объект
         */

        /** Eng
         * SkyHistory(historyWord) -
         * Creating an instance of the History class and passing it to it
         * empty History list
         * testHistory - link to the newly created object
         */

        /** 中文
         * SkyHistory(historyWord) -
         * 创建History类的实例并将其传递给它
         * 空历史列表
         * testHistory - с链接到新创建的对象
         */
        val testHistory = SkyHistory(historyMeaning=historyMeaning)

        /** Русский
         * Вызовите свою функцию
         * Вызываем из новосозданного экземпляра функцию, которую тестируем
         * В ответ дает размер истории (Int), который загоняем в result
         */

        /** Eng
         * Call your function
         * Calling the function that we are testing from the newly created instance
         * In response, it gives the size of the history (Int), which we drive into the result
         */

        /** 中文
         *调用函数
         *从新创建的实例调用我们正在测试的函数
         *作为响应，它给出了历史记录的大小（Int），我们将其驱动到结果中
         */
        val result = testHistory.sizeHistoryMeaning()

        /** Русский
         * Проверьте результат
         * Штатный вопрос JUnit на равенство
         * assert - утверждаю Equals - что равно 500
         */

        /** Eng
         * Check the result
         * JUnit staff question on equality
         * assert-I claim Equals - which is equal to 500
         */

        /** 中文
         * 检查结果
         * 员工问题 JUnit 关于平等
         * assert - 我同意 Equals - 什么等于 500
         */
        assertEquals(result, 500)

        /** Русский
         * Проверьте результат
         * Штатный вопрос hamcrest на равенство
         * assert - утверждаю Equals - что равно 500
         */

        /** Eng
         * Check the result
         * hamcrest staff question on equality
         * assert-I claim Equals - which is equal to 500
         */

        /** 中文
         * 检查结果
         * 员工问题 hamcrest 关于平等
         * assert - 我同意 Equals - 什么等于 500
         */
        assertThat(result, `is`(500))
    }

    /** Русский
     * Тест: что размер истории из 2 147 483 647 записей создастся не может и будет превышение
     * размера кучи
     */

    /** Eng
     * Test: that the history size of 2 147 483 647 records will not be created and will exceed
     * heap size
     */

    /** 中文
     * 测试：不会创建 2 147 483 647条记录的历史记录大小，并且将超过
     * 堆大小
     */
    @Test
    @Ignore
    fun sizeHistoryMeaning_LotMeanings_equalNoSpace() {
        /** Русский
         * Создаем пустую историю
         */

        /** Eng
         * Creating an empty history
         */

        /** 中文
         *创建一个空的历史记录
         */
        val historyMeaning: MutableSet<Meaning> = arraySetOf()

        /** Русский
         * В пустой список нагоняем записи.
         * Создаем тестовый случай
         * * остальные поля записи генерируются по умолчанию
         * На каком-то i память и закончится и будет ошибка
         */

        /** Eng
         * In an empty list, we catch up with the entries.
         * Creating a test case
         * * other fields of the record are generated by default
         * At some point i memory will run out and there will be an error
         */

        /** 中文
         *在一个空列表中，我们赶上了条目。
         *创建测试用例
         **记录的其他字段默认生成
         *在某些时候，我的内存将耗尽，会有一个错误
         */
        for(i in 1..Int.MAX_VALUE){
            historyMeaning.add(Meaning(text="1938 $i"))
        }

        /** Русский
         * Создаем экземпляр класса История и передаем ему
         * не пустой список Истории
         * testHistory - ссылка на ново созданный объект
         * На самом деле, сюда программа не доходит, уже ломается
         */

        /** Eng
         * Creating an instance of the History class and passing it to it
         * not an empty History list
         * testHistory - link to the newly created object
         * In fact, the program does not reach here, it already breaks down
         */

        /** 中文
         * 创建History类的实例并将其传递给它
         *不是空的历史列表
         * testHistory - 链接到新创建的对象
         *事实上，程序没有到达这里，它已经分解了
         */
        val testHistory = SkyHistory(historyMeaning=historyMeaning)
        /** Русский
         * Вызовите свою функцию
         * Вызываем из новосозданного экземпляра функцию, которую тестируем
         * В ответ дает размер истории (Int), который загоняем в result
         * На самом деле, сюда программа не доходит, уже ломается
         */

        /** Eng
         * Call your function
         * We call the function that we are testing from the newly created instance
         * In response, it gives the size of the history (Int), which we drive into the result
         * In fact, the program does not reach here, it already breaks down
         */

        /** 中文
         *调用函数
         *从新创建的实例调用我们正在测试的函数
         *作为响应，它给出了历史记录的大小（Int），我们将其驱动到结果中
         *事实上，程序没有到达这里，它已经分解了
         */
        val result = testHistory.sizeHistoryMeaning()
        /** Русский
         * Проверьте результат
         * Штатный вопрос JUnit на равенство
         * assert - утверждаю Equals - что
         * TODO Сделать правильный вопрос на ошибку
         */

        /** Eng
         * Check the result
         * JUnit staff question on equality
         * assert-I claim Equals - which
         * TODO making the right question on a mistake
         */

        /** 中文
         * 检查结果
         * 员工问题 JUnit 关于平等
         * assert - 我同意 Equals - 什么
         * TODO 对错误做出正确的问题
         */
        assertEquals(result, Int.MAX_VALUE)
    }
}