package com.fakhril.ivy.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey(autoGenerate = true)var id: Int = 0,
val itemName : String,
val total : Int,
val idPlace: Int
)
