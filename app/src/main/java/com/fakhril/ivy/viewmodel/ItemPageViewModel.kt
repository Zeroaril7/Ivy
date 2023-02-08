package com.fakhril.ivy.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.fakhril.ivy.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemPageViewModel(application: Application): AndroidViewModel(application) {

    val getPlace: LiveData<List<Place>>
    val getItem: LiveData<List<Item>>
    val repository : IvyRepository

    init {
        val dao = IvyDatabase.getDatabase(application).getIvyDao()
        repository = IvyRepository(dao)
        getItem = repository.allItemData
        getPlace = repository.allPlaceData
    }

   fun insertItem(item: Item) = viewModelScope.launch(Dispatchers.IO){
       repository.insertItem(item)
   }

    fun deleteItem(item: Item) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteItem(item)
    }

    fun updateItem(item: Item) = viewModelScope.launch(Dispatchers.IO){
        repository.updateItem(item)
    }



}