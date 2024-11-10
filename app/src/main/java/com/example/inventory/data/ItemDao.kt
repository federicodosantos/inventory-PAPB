package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/*
Interface ini merupakan Data Access Object(DAO) yang berfungsi sebagai tempat pendefinisian
metode-metode yang digunakan untuk berinteraksi dengan table items di database room.
 */
@Dao
interface ItemDao {

    /*
    Fungsi ini digunakan untuk menambahkan item baru ke dalam table items di database.
    Dengan menggunakan argumen OnConflictStrategy.IGNORE, kita dapat menangani konflik
    jika ada item yang memiliki primary key yang sama sudah ada, maka operasi penambahan akan diabaikan
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    /*
    Fungsi ini digunakan untuk memperbarui data item yang sudah ada di database.
    Jadi nantinya room akan mencari item berdasarkan id atau primary yang sudah ada dan memperbaruinya
    dengan data yang diinputkan pengguna.
     */
    @Update
    suspend fun update(item: Item)

    /*
    Fungsi ini digunakan untuk menghapus item dari database.
    Jadi nantinya room akan mencari item berdasarkan id atau primary key yang sudah ada dan menghapus item tersebut
     */
    @Delete
    suspend fun delete(item: Item)

    /*
    Fungsi ini digunakan untuk mengambil item berdasarkan id yang diberikan.
    Jadi, fungsi ini akan mengembalikan Flow<Item> sehingga setiap kali item tersebut diperbarui
    maka perubahan ini akan otomatis diterima.
     */
    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    /*
    Fungsi ini digunakan untuk mengambil seluruh item yang ada pada table items dengan urutan naik.
    Jadi, fungsi ini akan mengembalikan Flow<List<Item>> yang memungkinkan aplikasi kita untuk menerima
    pembaruan otomaris saat data diperbarui.
     */
    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
}