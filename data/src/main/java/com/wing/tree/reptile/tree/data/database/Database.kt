package com.wing.tree.reptile.tree.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wing.tree.reptile.tree.data.dao.ProfileDao
import com.wing.tree.reptile.tree.data.entity.Profile
import com.wing.tree.reptile.tree.data.typeconverters.TypeConverters

@androidx.room.Database(entities = [Profile::class], exportSchema = false, version = 1)
@androidx.room.TypeConverters(TypeConverters::class)
abstract class Database: RoomDatabase() {
    abstract fun profileDao(): ProfileDao

    companion object {
        private const val PACKAGE_NAME = "com.wing.tree.reptile.tree.data.database"
        private const val CLASS_NAME = "Database"
        private const val NAME = "$PACKAGE_NAME.$CLASS_NAME"
        private const val VERSION = "1.0.0"

        @Volatile
        private var INSTANCE: Database? = null

        fun instance(context: Context): Database {
            synchronized(this) {
                return INSTANCE ?: run {
                    Room.databaseBuilder(
                        context.applicationContext,
                        Database::class.java,
                        "$NAME.$VERSION"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                        .also {
                            INSTANCE = it
                        }
                }
            }
        }
    }
}