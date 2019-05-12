package com.drewbitt.trntlist.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.drewbitt.trntlist.data.model.TrntJson

@Dao
interface TrntJsonDao {

    @Query("select * from TrntJson")
    fun getAllTrntJson(): List<TrntJson>

    @Query("select * from TrntJson")
    fun getAllTrntJsonLiveData(): LiveData<List<TrntJson>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrntJson(json: TrntJson)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTrntJson(json: TrntJson)
}