package com.fakhril.ivy.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.fakhril.ivy.database.IvyDatabase
import com.fakhril.ivy.database.IvyRepository
import com.fakhril.ivy.database.Place
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlacePageViewModel(application: Application): AndroidViewModel(application) {

    val getPlace: LiveData<List<Place>>
    val getPlaceLimit : LiveData<List<Place>>
    val repository: IvyRepository

    init {
        val dao = IvyDatabase.getDatabase(application).getIvyDao()
        repository = IvyRepository(dao)
        getPlace = repository.allPlaceData
        getPlaceLimit = repository.limitPlace
    }

    fun insertPlace(place: Place) = viewModelScope.launch (Dispatchers.IO){
        repository.insertPlace(place)
    }

    fun deletePlace(place: Place) = viewModelScope.launch (Dispatchers.IO){
        repository.deletePlace(place)
    }

    fun updatePlace(place: Place) = viewModelScope.launch (Dispatchers.IO){
        repository.updatePlace(place)
    }
}