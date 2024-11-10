package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
Class InventoryDatabase adalaha kelas abstrak yang mewarisi dari Room database.
Class ini berfungsi sebagai representasi utama database room dalam aplikasi.
Anotasi @Database digunakan untuk menunjukkan bahwa class ini merupakan database room.
Pada argumen anotasi @Database menunjukkan bahwa InventoryDatabase mencakup table items
, menggunakan database versi, dan exportSchema yang bernilai false.
 */
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {

    /*
    Fungsi ini idigunakan untuk akses ke metode DAO yang telah didefinisikan dalam interface ItemDao.
     */
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var Instance: InventoryDatabase? = null
        /*
        Fungsi ini digunakan untuk mengembalikan instance singleton InventoryDatabase.
        Jika instanace tidak null, maka instance tersebut dikembalikan.
        Jika null maka blok synchronized akan membuat instance baru dari database.
         */
        fun getDatabase(context: Context): InventoryDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}