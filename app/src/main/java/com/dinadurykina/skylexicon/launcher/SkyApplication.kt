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
// Все комментарии есть в ToDo
package com.dinadurykina.skylexicon.launcher

import android.app.Application
//import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

//@HiltAndroidApp
class SkyApplication : Application() {
    val applicationContext = this
    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.Default).launch {
            timberInit()  // Инициализировать Timber не блокирует основной поток:+ две задачи
        }
    }
}
    // Инициализация Timber
    private fun timberInit() {
        Timber.plant(Timber.DebugTree())
        Timber.i("SkyApplication timber READY ")
    }
