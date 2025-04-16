package com.example.th2.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    fun getTaskById(taskId: Int): Flow<TaskEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task : TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)
} 