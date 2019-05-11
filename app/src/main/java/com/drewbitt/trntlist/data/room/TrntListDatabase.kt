package com.drewbitt.trntlist.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.drewbitt.trntlist.data.model.TrntJson

@Database(entities = [(TrntJson::class)], version = 1, exportSchema = false)
@TypeConverters(GithubTypeConverters::class)
abstract class TrntListDatabase: RoomDatabase() {
    abstract fun trntJsonDao(): TrntJsonDao
}