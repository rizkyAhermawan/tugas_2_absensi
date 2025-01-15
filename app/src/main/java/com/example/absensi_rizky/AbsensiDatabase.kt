package com.example.absensi_rizky

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Absensi::class], version = 1)
abstract class AbsensiDatabase : RoomDatabase() {
    abstract fun absensiDao(): AbsensiDao

    companion object {
        @Volatile
        private var INSTANCE: AbsensiDatabase? = null

        fun getDatabase(context: Context): AbsensiDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AbsensiDatabase::class.java,
                    "absensi_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
