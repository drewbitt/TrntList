package com.drewbitt.trntlist.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.drewbitt.trntlist.data.model.TrntJson

@Dao
interface TrntJsonDao {

    @Query("select * from TrntJson")
    fun getAllTrntJson(): List<TrntJson>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrntJson(json: TrntJson)
}