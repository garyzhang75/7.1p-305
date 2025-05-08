package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LostFoundItem::class], version = 2, exportSchema = false)
abstract class LostFoundDatabase : RoomDatabase() {

    abstract fun lostFoundDao(): LostFoundDao

    companion object {
        @Volatile
        private var INSTANCE: LostFoundDatabase? = null

        fun getDatabase(context: Context): LostFoundDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LostFoundDatabase::class.java,
                    "lost_found_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
