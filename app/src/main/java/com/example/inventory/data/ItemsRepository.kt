/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.data

import kotlinx.coroutines.flow.Flow

/*
Interface ini digunakan untuk mneyediakan metode untuk mengelola data Item.
 */
interface ItemsRepository {
    /*
    Metode ini digunakan untuk mendapatkan daftar item dari database.
    Jadi metode ini akan mengambil semua item dari sumber data dan mengembalikannya dalam betuk Flow.
     */
    fun getAllItemsStream(): Flow<List<Item>>

    /*
    Metode ini digunakan untuk mengambil satu item dari sumber data berdasarkan id yang dimasukkan pengguna.
     */
    fun getItemStream(id: Int): Flow<Item?>

    /*
    Metode ini digunakan untuk menambahkan item ke dalam sumber data.
     */
    suspend fun insertItem(item: Item)

    /*
    Metode ini digunakan untuk menghapus item dari sumber data.
     */
    suspend fun deleteItem(item: Item)

    /*
    Metode ini digunakan untuk memperbaru item yang ada di sumber data.
     */
    suspend fun updateItem(item: Item)
}
