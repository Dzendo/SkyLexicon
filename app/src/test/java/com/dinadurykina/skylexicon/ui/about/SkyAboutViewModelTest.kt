package com.dinadurykina.skylexicon.ui.about

/**
 * Добавьте ядро AndroidX Test и зависимости ext
 * Добавить зависимость библиотеки Robolectric Testing
 * Аннотируйте класс с помощью средства запуска тестов AndroidJunit4
 * Напишите тестовый код AndroidX
 * Добавьте средство запуска тестов JUnit
 * Используйте AndroidX Test
 */
/**
 * Для проверки LiveData рекомендуется сделать две вещи:
 * Использовать InstantTaskExecutorRule
 * Обеспечить LiveData наблюдение
 */
/**
 * Добавьте класс LiveDataTestUtil.kt
 * Используйте getOrAwaitValue, чтобы написать утверждение.
 */


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dinadurykina.skylexicon.getOrAwaitValue



import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
// import org.hamcrest.Matchers.`is`
// import org.hamcrest.core.IsNull.nullValue
// import org.hamcrest.CoreMatchers.not

import org.junit.*
/*import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
*/
import org.junit.runner.RunWith


import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
//@Config(sdk = [Build.VERSION_CODES.O_MR1])
@Config(manifest=Config.NONE)
class SkyAboutViewModelTest {
    // Когда вы пишете тесты, включающие тестирование LiveData, используйте это правило!
    // @get:Rule аннотацией, он вызывает InstantTaskExecutorRule -
    // запуск некоторого кода в классе до и после тестов
    // Это правило запускает все фоновые задания, связанные с компонентами архитектуры, в одном потоке,
    // чтобы результаты теста выполнялись синхронно и в повторяющемся порядке
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Subject under test
    private lateinit var skyAboutViewModel: SkyAboutViewModel

    @Before
    fun setUp() {
        // Given a fresh ViewModel
        skyAboutViewModel = SkyAboutViewModel(ApplicationProvider.getApplicationContext())
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getMyName() {
    }

    @Test
    fun getEditNickname() {
    }

    @Test
    fun getDoneButton() {
    }

    @Test
    fun getNicknameText() {
    }

    @Test
    fun onDoneButtonClick() {


        // When Click button
        skyAboutViewModel.onDoneButtonClick()
        // Then the event is triggered
        // На этом шаге вы используете getOrAwaitValue метод и пишете инструкцию assert,
        // которая проверяет, newTaskEvent был ли запущен.
        // Then the new task event is triggered Затем запускается новое событие задачи
        // тодо test LiveData см LiveDataTestUtil

        assertThat(skyAboutViewModel.testS,`is`("ПапаМама"))
        assertThat(skyAboutViewModel.doneButton.value,`is`(true))  // плохо

        val doneButton = skyAboutViewModel.doneButton.getOrAwaitValue()
        assertThat(doneButton, not(nullValue()))
        assertThat(doneButton,`is`(true))

        val editNickname = skyAboutViewModel.editNickname.getOrAwaitValue()
        assertThat(editNickname, not(nullValue()))
        assertThat(editNickname,`is`(false))

        //MatcherAssert.assertThat(value.getContentIfNotHandled(), Matchers.not(Matchers.nullValue()))
        // Что такое getContentIfNoteHandled?
        //В приложении TO-DO вы используете настраиваемый Event класс для LiveData представления одноразовых событий
        // (таких как навигация или всплывающая закусочная) getContentIfNotHandled предоставляет «разовую» возможность.
        // При первом вызове он получает содержимое файла Event.
        // Любые дополнительные вызовы getContentIfNotHandled того же контента будут возвращены null.
        // Вот как Event осуществляется доступ к данным в коде приложения, и поэтому мы используем его для тестов.
        // Вы можете узнать больше о событиях здесь .
        // https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150
    }

    @Test
    fun onDoneButtonClicked() {
    }

    @Test
    fun onNicknameTextClick() {
    }

    @Test
    fun onNicknameTextClicked() {
    }
}