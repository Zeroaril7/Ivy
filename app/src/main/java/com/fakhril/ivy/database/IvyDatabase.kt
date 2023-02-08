package com.fakhril.ivy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Place::class, Item::class],
    version = 9,
    exportSchema = false
)
abstract class IvyDatabase : RoomDatabase() {
    abstract fun getIvyDao(): IvyDao

    companion object {
        @Volatile
        private var INSTANCE: IvyDatabase? = null

        fun getDatabase(context: Context): IvyDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    IvyDatabase::class.java,
                    "ivy_db"
                ).fallbackToDestructiveMigration()
                    .build().also {
                    INSTANCE = it
                }
            }
        }
    }
}