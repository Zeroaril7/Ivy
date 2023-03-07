package com.fakhril.ivy.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface IvyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlace(place: Place)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(item: Item)

    @Update
    suspend fun updatePlace(place: Place)

    @Update
    suspend fun updateItem(item: Item)

    @Delete
    suspend fun deletePlace(place: Place)

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("SELECT * FROM place")
    fun getPlace(): LiveData<List<Place>>

    @Query("SELECT * FROM item ORDER BY id DESC")
    fun getItem(): LiveData<List<Item>>

    @Query("SELECT * FROM place LIMIT 2")
    fun getPlaceLimit() : LiveData<List<Place>>

    @Query("SELECT * FROM item LIMIT 4")
    fun getItemLimit() : LiveData<List<Item>>

    @Query("DELETE FROM item WHERE placeName = :placeName")
    fun deleteSelectItem(placeName: String)
}