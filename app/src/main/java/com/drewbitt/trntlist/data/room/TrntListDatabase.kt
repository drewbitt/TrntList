package com.drewbitt.trntlist.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.drewbitt.trntlist.data.model.TrntJson

@Database(entities = [(TrntJson::class)], version = 1, exportSchema = false)
abstract class TrntListDatabase: RoomDatabase() {
    abstract fun trntJsonDao(): TrntJsonDao
}