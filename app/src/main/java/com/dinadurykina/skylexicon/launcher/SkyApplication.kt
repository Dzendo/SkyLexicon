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
package com.dinadurykina.skylexicon.launcher

import android.app.Application
import com.dinadurykina.skylexicon.repository.SkyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * onCreate is called before the first screen is shown to the user.
 * onCreate Application вызывается до того, как пользователю будет показан первый экран.
 *
 * Use it to setup any background tasks, running expensive setup operations in a background
 * thread to avoid delaying app start.
 * Используйте его для настройки любых фоновых задач, выполняя дорогостоящие операции настройки
 * в фоновом режиме поток, чтобы избежать задержки запуска приложения.
 *
 * Этот Application вызывается сразу из манифеста, т.к. android:name=".launcher.ToDoApplication",
 * а после него вызывается android:name=".launcher.ToDoActivity"
 * А если в Application стартовать фоновый поток, то он начнет выполняться, но в фоне, т.е.
 * не дожадаясь его окончания стартует активити;
 * А можно много потоков стартовать, а можно службы и сервисы здесь стартовать, но в фоне
 * А тогда они будут работать параллельно с клиентской активити.
 */
// https://developer.android.com/codelabs/advanced-android-kotlin-training-testing-basics?index=..%2F..index#4
class SkyApplication : Application() {
    val applicationContext = this  // может пригодиться где-нибудь
    // Важно, чтобы вы всегда создавали только один экземпляр класса репозитория.
    // Чтобы в этом убедиться, вы воспользуетесь локатором служб в классе TodoApplication.
    // назначьте ему репозиторий, полученный с использованием ServiceLocator.provideTaskRepository
    val skyRepository: SkyRepository = SkyRepository()
        get() = field  //ServiceLocator.provideTasksRepository(this)


    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.Default).launch {
            timberInit()  // Инициализировать Timber (не блокирует основной поток)
        }
    }
}
    // Инициализация Timber
    private fun timberInit() {
        Timber.plant(Timber.DebugTree())
        Timber.i("SkyApplication timber READY ")
    }

/**
 * Не обращаем внимания что Android Studio помечает Application() серым - как невызываемый
 * Стартует Application Androidом по указанию из манифеста:
 *  <application
 *  android:name="com.dinadurykina.skylexicon.launcher.SkyApplication"
 *  ........
 *   </application>
 *  Android Studio этого не видит и тупит - мелчайший багчик
 * Application стартует самый первый - на экране ничего не появляется
 * Умирает она последней поэтому в ней что-нибудь размещать на жизнь всей программы
 * # Если в ней стартовать потоки, то они будут жить параллельно с остальной программой
 * № Например птичку, бота, службу какую-то, навязчивую рекламу, которая все время всплывает.
 * Архитектура рекомендует здесь размещать запланированные работы
 */