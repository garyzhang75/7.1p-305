package com.example.myapplication

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LostFoundViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LostFoundRepository

    val allItems: LiveData<List<LostFoundItem>>

    init {
        val dao = LostFoundDatabase.getDatabase(application).lostFoundDao()
        repository = LostFoundRepository(dao)
        allItems = repository.allItems
    }

    fun insert(item: LostFoundItem) = viewModelScope.launch {
        repository.insert(item)
    }

    fun update(item: LostFoundItem) = viewModelScope.launch {
        repository.update(item)
    }

    fun delete(item: LostFoundItem) = viewModelScope.launch {
        repository.delete(item)
    }

    suspend fun getItemById(id: Int): LostFoundItem? {
        return repository.getItemById(id)
    }
}