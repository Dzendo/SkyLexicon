/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dinadurykina.skylexicon.network

/**
 * A generic class that holds a value with its loading status.
 * Универсальный класс, который содержит значение со статусом загрузки.
 * @param <T>
 *     https://github.com/Kotlin/KEEP/blob/497c86126b7320f3651807eb070f5efe80bcdb3a/proposals/stdlib/result.md
 *     https://github.com/Kotlin/KEEP/commit/497c86126b7320f3651807eb070f5efe80bcdb3a
 *     https://github.com/Kotlin/KEEP/blob/497c86126b7320f3651807eb070f5efe80bcdb3a/proposals/stdlib/result.md
 *     N:\_AndroidGradle\caches\modules-2\files-2.1\org.jetbrains.kotlin\kotlin-stdlib-common\1.5.10\86ff32fa15b7f66f3b5ced4164c2311c0530adde\kotlin-stdlib-common-1.5.10-sources.jar!\kotlin\util\Result.kt
 *     \kotlin-stdlib-common-1.5.10-sources.jar!\kotlin\util\Result.kt
 *
 */
sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String =
        when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }

}

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 * 'true', если [Result] имеет тип [Success] и содержит ненулевые [Success.data].
 */
val Result<*>.succeeded
    get() = this is Result.Success && data != null

// Not obligatory
fun <T> Result<T>.successOr(fallback: T): T = (this as? Result.Success<T>)?.data ?: fallback


/*
 private suspend fun getTasks(forceUpdate: Boolean = false): Result<List<Task>> {
  fun observeTasks(): LiveData<Result<List<Task>>> {
        return tasksLocalDataSource.observeTasks()
private val observableTasks = MutableLiveData<Result<List<Task>>>()
 override fun observeTasks(): LiveData<Result<List<Task>>> {
        return observableTasks
override fun observeTask(taskId: String): LiveData<Result<Task>> {
        return observableTasks.map { tasks ->
            when (tasks) {
                is Result.Loading -> Result.Loading
                is Error -> Error(tasks.exception)
                is Success -> {
                    val task = tasks.data.firstOrNull { it.id == taskId }
                        ?: return@map Error(Exception("Not found"))
                    Success(task)
                }
            }
        }
override suspend fun getTask(taskId: String): Result<Task> {
        // Simulate network by delaying the execution.
        // Имитация сети путем задержки выполнения.
        delay(SERVICE_LATENCY_IN_MILLIS)
        TASKS_SERVICE_DATA[taskId]?.let {
            return Success(it)
        }
        return Error(Exception("Task not found"))
    }
 */