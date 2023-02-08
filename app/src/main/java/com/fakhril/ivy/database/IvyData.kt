package com.fakhril.ivy.database

import androidx.room.Embedded
import androidx.room.Relation

data class IvyData(
    @Embedded val place: Place,
    @Relation(
        parentColumn = "idPlace",
        entityColumn = "idPlace",
        entity = Item::class
    )
    val item: List<Item>
)
