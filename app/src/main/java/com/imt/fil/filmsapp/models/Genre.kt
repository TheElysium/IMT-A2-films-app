package com.imt.fil.filmsapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Genre @Ignore constructor(
    @PrimaryKey val id: Int = -1,
    val name: String? = null,
) : Parcelable {
}
