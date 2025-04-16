package com.example.th2

import com.google.gson.annotations.SerializedName

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    @SerializedName("status") val status: String,
    @SerializedName("priority") val priority: String,
    @SerializedName("category") val category: String,
    @SerializedName("dueDate") val dueDate: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String,
    @SerializedName("subtasks") val subtasks: List<Subtask>? = null,
    @SerializedName("attachments") val attachments: List<Attachment>? = null,
    @SerializedName("reminders") val reminders: List<Reminder>? = null
)

data class Subtask(
    val id: Int,
    val title: String,
    @SerializedName("isCompleted") val isCompleted: Boolean
)

data class Attachment(
    val id: Int,
    @SerializedName("fileName") val fileName: String,
    @SerializedName("fileUrl") val fileUrl: String
)

data class Reminder(
    val id: Int,
    val time: String,
    val type: String
)
data class TaskUiState(
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)