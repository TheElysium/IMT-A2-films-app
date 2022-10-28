package com.imt.fil.filmsapp.models

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class CastMember(
    val name: String? = null
) : Parcelable