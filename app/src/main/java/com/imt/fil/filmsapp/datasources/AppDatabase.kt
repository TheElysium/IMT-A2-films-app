package com.imt.fil.filmsapp.datasources

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.imt.fil.filmsapp.converters.LocalDateConverter
import com.imt.fil.filmsapp.datasources.dao.MovieDao
import com.imt.fil.filmsapp.models.Movie

@Database(entities = [Movie::class], version = 1)
@TypeConverters(LocalDateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}