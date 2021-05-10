package com.dinadurykina.skylexicon.launcher

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.dinadurykina.skylexicon.R
import com.dinadurykina.skylexicon.databinding.ActivitySkyBinding
import timber.log.Timber

/**
 * Создается новый класс SkyActivity с родителем AppCompatActivity
 * используется binding, для этого в build.gradle должно стоять хотя бы одно из двух:
 *  buildFeatures {
 *        dataBinding true
 *        viewBinding true  // Используем в Activity
 *    }
 */



class SkyActivity : AppCompatActivity() {
    // Выделяется место под эти переменные в созданном классе
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mainBinding: ActivitySkyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Надувает главный экран из своего activity_sky.xml и запоминает адрес в переменной mainBinding
        mainBinding = ActivitySkyBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        // Добавляет меню три точки для этого фрагмента на месте toolbar указанному в xml
        setSupportActionBar(mainBinding.toolbar)  // Задать toolbar, что у него есть три точки через xml
        // надувание этого меню см ниже в onCreateOptionsMenu потом
        // обработка этого см. еще ниже в onOptionsItemSelected

        // Просим ссылку на загрузчик фрагментов и говорим ему, что грузить он будет в поле @+id/nav_host_fragment
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(mainBinding.navHostFragment.id) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        // верхнему бару говорит,что у него будет вызывная шторка и она указана drawerLayout
        // а переходить надо будет контроллером навигации, а он знает куда
        appBarConfiguration = AppBarConfiguration(navController.graph, mainBinding.drawerLayout)
        // Полю @+id/toolbar пришивается setupWithNavController с конфигурацией построенной выше
        mainBinding.toolbar.setupWithNavController(navController, appBarConfiguration)
        // Полю @+id/nav_view ему звать navController, когда на него нажмут, что бы перейти куда нажали
        mainBinding.navView.setupWithNavController(navController)

        // отправляет в логкат SkyMainActivity onCreate
        Timber.i("SkyMainActivity onCreate ")
    // onCreate закончен, все сделали отдаем управление Андроиду и он отдает юзеру
    }

    // Т.к. в onCreate мы сказали setSupportActionBar(mainBinding.toolbar), то
    // Андроид вызовет onCreateOptionsMenu, а мы его здесь переопределим.
    // Добавляет меню три точки для этого фрагмента
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        // Раздуйте меню: это добавит элементы в панель действий, если она присутствует.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    // Обрабатывает меню три точки для этого фрагмента при нажатии на элемент меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
    // Меню MenuItem присылает имя поля из XML, которое нажали
        // По уму этот обработчик надо бы переместить в SkyActivityViewModel, но технологии биндинг для меню не создано
        // Обещают сделать, тогда переместим в ViewModel
        return when (item.itemId) {
            R.id.action_settings -> {Toast.makeText(applicationContext, "Пошел Settings", Toast.LENGTH_LONG).show(); true }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

