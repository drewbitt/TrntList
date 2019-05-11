package com.drewbitt.trntlist.data.room

import androidx.room.*
import com.drewbitt.trntlist.data.model.TrntJson

@Dao
interface TrntJsonDao {

    @Query("select * from TrntJson")
    fun getAllTrntJson(): List<TrntJson>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrntJson(json: TrntJson)

    @Update
    fun updateTrntJson(json: TrntJson)
}