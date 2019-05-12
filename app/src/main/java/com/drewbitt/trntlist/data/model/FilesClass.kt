package com.drewbitt.trntlist.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*TODO:
Not only would an implementation for files need this class, it would also need a custom converter since it's list
of Filesclass*/

@Parcelize
data class FilesClass(
    var path: String = "",
    var name: String = "",
    var length: Int = 0,
    var offset: Int = 0
) : Parcelable