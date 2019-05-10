package com.drewbitt.trntlist.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity

data class TrntJson(
    @PrimaryKey(autoGenerate = true) override var autoId: Int,
    override var name: String,
    override var created: String,
    override var private: Boolean,
    override var announce: MutableList<String>,
    override var infoHash: String,
    override var files: Map<String, String>
): TrntJsonData(), Parcelable
