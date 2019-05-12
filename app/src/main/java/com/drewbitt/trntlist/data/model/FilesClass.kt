package com.drewbitt.trntlist.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilesClass(
    var path: String = "",
    var name: String = "",
    var length: Int = 0,
    var offset: Int = 0
) : Parcelable