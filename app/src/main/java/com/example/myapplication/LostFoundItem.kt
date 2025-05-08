package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lost_found")
data class LostFoundItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val phone: String,
    val description: String,
    val date: String,
    val location: String,
    val type: String // "Lost" or "Found"
)
