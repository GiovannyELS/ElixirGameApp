package com.example.elixirgameapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.elixirgameapp.data.local.dao.VideoGameDao
import com.example.elixirgameapp.data.response.VideoGameDetailsResponse
import com.example.elixirgameapp.data.response.VideoGameResponse


@Database(
    entities = [VideoGameResponse::class, VideoGameDetailsResponse::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun videoGameDAO(): VideoGameDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "video_game_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}