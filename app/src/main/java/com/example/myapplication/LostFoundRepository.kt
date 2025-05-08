package com.example.myapplication

import androidx.lifecycle.LiveData

class LostFoundRepository(private val dao: LostFoundDao) {

    val allItems: LiveData<List<LostFoundItem>> = dao.getAllItems()

    suspend fun insert(item: LostFoundItem) {
        dao.insert(item)
    }

    suspend fun update(item: LostFoundItem) {
        dao.update(item)
    }

    suspend fun delete(item: LostFoundItem) {
        dao.delete(item)
    }

    suspend fun getItemById(id: Int): LostFoundItem? {
        return dao.getItemById(id)
    }
}
