package com.fakhril.ivy.database

import androidx.room.*

@Entity
data class Place(
    @PrimaryKey(autoGenerate = true)var idPlace: Int = 0,
    var placeName : String,
)
