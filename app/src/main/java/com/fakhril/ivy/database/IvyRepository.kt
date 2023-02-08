package com.fakhril.ivy.database

import androidx.lifecycle.LiveData

class IvyRepository(private val ivyDao: IvyDao) {

    val allPlaceData : LiveData<List<Place>> = ivyDao.getPlace()
    val allItemData : LiveData<List<Item>> = ivyDao.getItem()

    suspend fun insertPlace(place: Place){
        ivyDao.insertPlace(place)
    }

    suspend fun insertItem(item: Item){
        ivyDao.insertItem(item)
    }

    suspend fun deletePlace(place: Place){
        ivyDao.deletePlace(place)
    }

    suspend fun deleteItem(item: Item){
        ivyDao.deleteItem(item)
    }

    suspend fun updatePlace(place: Place){
        ivyDao.updatePlace(place)
    }

    suspend fun updateItem(item: Item){
        ivyDao.updateItem(item)
    }


}