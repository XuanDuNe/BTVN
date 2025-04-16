package com.example.th2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val status: String = "TODO",
    val priority: String = "MEDIUM",
    val category: String = "WORK",
    val dueDate: String = "",
    val createdAt: String = "",
    val updatedAt: String = ""
) 