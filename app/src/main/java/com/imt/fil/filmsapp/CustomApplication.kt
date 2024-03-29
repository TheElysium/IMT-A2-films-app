package com.imt.fil.filmsapp

import android.app.Application
import androidx.room.Room
import com.imt.fil.filmsapp.datasources.AppDatabase

class CustomApplication : Application() {

    private lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "my-database",
        ).build()
    }

    fun getDb(): AppDatabase = database
}