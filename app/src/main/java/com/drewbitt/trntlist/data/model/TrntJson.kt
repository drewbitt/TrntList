package com.drewbitt.trntlist.data.model

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
@Entity
data class TrntJson(
    @PrimaryKey(autoGenerate = true) override var autoId: Int = 0,
    override var name: String = "",
    override var created: String = "",
    override var private: Boolean = true,
    override var announce: List<String> = listOf(),
    override var infoHash: String = ""
    // override var files: Array<FilesClass> = arrayOf()
): TrntJsonData(), Parcelable
